package com.yun.job.handler;

/**
 * @author yun
 * @date 2024/11/16 16:07
 * @desciption:
 */

import cn.hutool.cache.Cache;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.yun.common.core.constants.CacheConstants;
import com.yun.common.core.constants.Constants;
import com.yun.common.redis.service.RedisService;
import com.yun.job.domain.exam.Exam;
import com.yun.job.domain.message.Message;
import com.yun.job.domain.message.MessageText;
import com.yun.job.domain.message.vo.MessageTextVO;
import com.yun.job.domain.user.UserScore;
import com.yun.job.domain.user.UserSubmit;
import com.yun.job.mapper.exam.IExamMapper;
import com.yun.job.mapper.user.IUserExamMapper;
import com.yun.job.mapper.user.IUserSubmitMapper;
import com.yun.job.service.IMessageService;
import com.yun.job.service.IMessageTextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.tags.EscapeBodyTag;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ExamXxlJob {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IExamMapper examMapper;

    @Autowired
    private IUserSubmitMapper userSubmitMapper;

    @Autowired
    private IMessageTextService messageTextService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IUserExamMapper userExamMapper;

    @XxlJob("examListOrganizeHandler")
    public void examListOrganizeHandler() {
        log.info("examListOrganizeHandler");
        List<Exam> unFinishList = examMapper.selectList(new LambdaQueryWrapper<Exam>()
                .select(Exam::getExamId, Exam::getTitle, Exam::getStartTime, Exam::getEndTime)
                .gt(Exam::getEndTime, LocalDateTime.now())
                .eq(Exam::getStatus, Constants.TRUE)
                .orderByDesc(Exam::getCreateTime));
        refreshCache(unFinishList, CacheConstants.EXAM_UNFINISHED_lIST);

        List<Exam> historyList = examMapper.selectList(new LambdaQueryWrapper<Exam>()
                .select(Exam::getExamId, Exam::getTitle, Exam::getStartTime, Exam::getEndTime)
                .le(Exam::getEndTime, LocalDateTime.now())
                .eq(Exam::getStatus, Constants.TRUE)
                .orderByDesc(Exam::getCreateTime));
        refreshCache(historyList, CacheConstants.EXAM_HISTORY_lIST);

    }

    @XxlJob("examResultHandler")
    public void examResultHandler() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime minusDateTime = now.minusDays(1);
        // 查找在这个时间范围内完赛的竞赛
        List<Exam> examList = examMapper.selectList(new LambdaQueryWrapper<Exam>()
                .select(Exam::getExamId, Exam::getTitle)
                .eq(Exam::getStatus, Constants.TRUE)
                .le(Exam::getEndTime, now)
                .ge(Exam::getStartTime, minusDateTime));
        if (CollectionUtil.isEmpty(examList)) {
            return;
        }

        Set<Long> examIdSet = examList.stream().map(Exam::getExamId).collect(Collectors.toSet());
        List<UserScore> userScoreList = userSubmitMapper.selectUserScoreList(examIdSet);
        Map<Long, List<UserScore>> userScoreMap = userScoreList.stream().collect(Collectors.groupingBy(UserScore::getExamId));
        createMessage(examList, userScoreMap);
    }

    private void createMessage(List<Exam> examList, Map<Long, List<UserScore>> userScoreMap) {
        List<MessageText> messageTextList = new ArrayList<>();
        List<Message> messageList = new ArrayList<>();
        for (Exam exam : examList) {
            Long examId = exam.getExamId();
            int examRank = 1;
            List<UserScore> userScoreList = userScoreMap.get(examId);
            int totalUser = userScoreList.size();
            for (UserScore userScore : userScoreList) {
                String msgTitle = exam.getTitle() + "————排名情况";
                String msgContent = "您所参与的竞赛：" + exam.getTitle() + "，本次竞赛一共"
                        + totalUser + "人，您排名第" + examRank + "名！";
                userScore.setExamRank(examRank);
                MessageText messageText = new MessageText();
                messageText.setMessageTitle(msgTitle);
                messageText.setMessageContent(msgContent);
                messageText.setCreateBy(Constants.SYSTEM_USER_ID);
                messageTextList.add(messageText);
                Message message = new Message();
                message.setSendId(Constants.SYSTEM_USER_ID);
                message.setCreateBy(Constants.SYSTEM_USER_ID);
                message.setRecId(userScore.getUserId());
                messageList.add(message);
                examRank++;
            }
            userExamMapper.updateUserScoreAndRank(userScoreList);
            redisService.rightPushAll(getExamRankListKey(examId), userScoreList);
        }
        messageTextService.batchInsert(messageTextList);
        Map<String, MessageTextVO> messageTextVOMap = new HashMap<>();
        for (int i = 0; i < messageTextList.size(); i++) {
            MessageText messageText = messageTextList.get(i);
            MessageTextVO messageTextVO = new MessageTextVO();
            BeanUtil.copyProperties(messageText, messageTextVO);
            String msgDetailKey = getMsgDetail(messageText.getTextId());
            messageTextVOMap.put(msgDetailKey, messageTextVO);
            Message message = messageList.get(i);
            message.setTextId(messageText.getTextId());
        }
        messageService.batchInsert(messageList);
        Map<Long, List<Message>> userMsgMap = messageList.stream().collect(Collectors.groupingBy(Message::getRecId));
        Iterator<Map.Entry<Long, List<Message>>> iterator = userMsgMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<Message>> entry = iterator.next();
            Long recId = entry.getKey();
            String userMsgListKey = getUserMsgListKey(recId);
            List<Long> userMsgTextIdList = entry.getValue().stream().map(Message::getTextId).toList();
            // 各个用户收到的消息id列表在缓存中被管理起来了
            redisService.rightPushAll(userMsgListKey, userMsgTextIdList);
        }
        redisService.multiSet(messageTextVOMap);
    }


    // 刷新缓存
    public void refreshCache(List<Exam> examList, String examListKey) {
        if (CollectionUtil.isEmpty(examList)) {
            return;
        }

        Map<String, Exam> examMap = new HashMap<>();
        List<Long> examIdList = new ArrayList<>();
        for (Exam exam : examList) {
            examMap.put(getDetailKey(exam.getExamId()), exam);
            examIdList.add(exam.getExamId());
        }
        redisService.multiSet(examMap);  //刷新详情缓存
        redisService.deleteObject(examListKey);
        redisService.rightPushAll(examListKey, examIdList);      //刷新列表缓存
    }

    private String getDetailKey(Long examId) {
        return CacheConstants.EXAM_DETAIL + examId;
    }

    private String getUserMsgListKey(Long userId) {
        return CacheConstants.USER_MESSAGE_LIST + userId;
    }

    private String getMsgDetail(Long textId) {
        return CacheConstants.MESSAGE_DETAIL + textId;
    }

    private String getExamRankListKey(Long examId) {
        return CacheConstants.EXAM_RANK_LIST + examId;
    }
}
