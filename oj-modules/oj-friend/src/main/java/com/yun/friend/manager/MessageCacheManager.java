package com.yun.friend.manager;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.yun.common.core.constants.CacheConstants;
import com.yun.common.core.domain.dto.PageQueryDTO;
import com.yun.common.core.enums.ExamListType;
import com.yun.common.redis.service.RedisService;
import com.yun.friend.domain.exam.dto.ExamQueryDTO;
import com.yun.friend.domain.exam.vo.ExamVO;
import com.yun.friend.domain.message.vo.MessageTextVO;
import com.yun.friend.mapper.message.IMessageTextMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yun
 * @date 2024/11/22 13:19
 * @desciption:
 */
@Component
public class MessageCacheManager {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IMessageTextMapper messageTextMapper;

    public Long getListSize(Long userId) {
        String userMsgListKey = getUserMsgListKey(userId);
        Long listSize = redisService.getListSize(userMsgListKey);
        return listSize;
    }

    private String getUserMsgListKey(Long userId) {
        return CacheConstants.USER_MESSAGE_LIST + userId;
    }

    private String getMsgDetail(Long textId) {
        return CacheConstants.MESSAGE_DETAIL + textId;
    }

    public void refreshCache(Long userId) {
        List<MessageTextVO> messageTextVOList = messageTextMapper.selectUserMsgList(userId);
        if (CollectionUtil.isEmpty(messageTextVOList)) {
            return;
        }
        List<Long> textIdList = messageTextVOList.stream().map(MessageTextVO::getTextId).toList();
        String userMsgListKey = getUserMsgListKey(userId);
        redisService.rightPushAll(userMsgListKey, textIdList);
        Map<String, MessageTextVO> messageTextVOMap = new HashMap<>();
        for (MessageTextVO messageTextVO : messageTextVOList) {
            messageTextVOMap.put(getMsgDetail(messageTextVO.getTextId()), messageTextVO);
        }

        redisService.multiSet(messageTextVOMap);
    }

    public List<MessageTextVO> getMsgTextVOList(PageQueryDTO dto, Long userId) {
        int start = (dto.getPageNum() - 1) * dto.getPageSize();
        int end = start + dto.getPageSize() - 1; //下标需要 -1
        String userMsgListKey = getUserMsgListKey(userId);
        List<Long> messageTextIdList = redisService.getCacheListByRange(userMsgListKey, start, end, Long.class);
        List<MessageTextVO> messageTextVOList = assembleMsgTextVOList(messageTextIdList);
        if (CollectionUtil.isEmpty(messageTextVOList)) {
            messageTextVOList = getMsgTextVOListByDB(dto, userId);
            refreshCache(userId);
        }
        return messageTextVOList;
    }

    private List<MessageTextVO> assembleMsgTextVOList(List<Long> messageTextIdList) {
        if (CollectionUtil.isEmpty(messageTextIdList)) {
            //说明redis当中没数据 从数据库中查数据并且重新刷新缓存
            return null;
        }
        //拼接redis当中key的方法 并且将拼接好的key存储到一个list中
        List<String> detailKeyList = new ArrayList<>();
        for (Long textId : messageTextIdList) {
            detailKeyList.add(getMsgDetail(textId));
        }
        List<MessageTextVO> messageTextVOList = redisService.multiGet(detailKeyList, MessageTextVO.class);
        CollUtil.removeNull(messageTextVOList);
        if (CollectionUtil.isEmpty(messageTextVOList) || messageTextVOList.size() != messageTextIdList.size()) {
            //说明redis中数据有问题 从数据库中查数据并且重新刷新缓存
            return null;
        }
        return messageTextVOList;
    }

    private List<MessageTextVO> getMsgTextVOListByDB(PageQueryDTO dto, Long userId) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return messageTextMapper.selectUserMsgList(userId);
    }
}
