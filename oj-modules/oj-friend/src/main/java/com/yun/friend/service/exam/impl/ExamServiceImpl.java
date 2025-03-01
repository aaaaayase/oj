package com.yun.friend.service.exam.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yun.common.core.constants.Constants;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.common.core.utils.ThreadLocalUtil;
import com.yun.friend.domain.exam.dto.ExamQueryDTO;
import com.yun.friend.domain.exam.dto.ExamRankDTO;
import com.yun.friend.domain.exam.vo.ExamRankVO;
import com.yun.friend.domain.exam.vo.ExamVO;
import com.yun.friend.domain.user.vo.UserVO;
import com.yun.friend.manager.ExamCacheManager;
import com.yun.friend.manager.UserCacheManager;
import com.yun.friend.mapper.exam.IExamMapper;
import com.yun.friend.mapper.user.IUserExamMapper;
import com.yun.friend.service.exam.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yun
 * @date 2024/11/15 15:38
 * @desciption: 竞赛相关业务逻辑
 */
@Service
public class ExamServiceImpl implements IExamService {
    @Autowired
    private IExamMapper examMapper;

    @Autowired
    private IUserExamMapper userExamMapper;

    @Autowired
    private ExamCacheManager examCacheManager;

    @Autowired
    private UserCacheManager userCacheManager;

    @Override
    public List<ExamVO> list(ExamQueryDTO examQueryDTO) {
        // 这一步按照我的理解是用于初始化你要分页的页码和题目数
        PageHelper.startPage(examQueryDTO.getPageNum(), examQueryDTO.getPageSize());
        // 当你查询出满足条件的结果时 pagehelper就会自动帮你给分
        return examMapper.selectExamList(examQueryDTO);
    }

    @Override
    public TableDataInfo redisList(ExamQueryDTO examQueryDTO) {
        // 从redis中去获取   竞赛列表的数据
        Long total = examCacheManager.getListSize(examQueryDTO.getType(), null);
        List<ExamVO> examVOList;
        if (total == null || total <= 0) {
            examVOList = list(examQueryDTO);
            examCacheManager.refreshCache(examQueryDTO.getType(), null);
            total = new PageInfo<>(examVOList).getTotal();
        } else {
            examVOList = examCacheManager.getExamVOList(examQueryDTO, null);
            total = examCacheManager.getListSize(examQueryDTO.getType(), null);
            if (examQueryDTO.getEndTime() != null && examQueryDTO.getStartTime() != null) {
                examVOList = examVOList.stream()
                        .filter(examVO -> examVO.getStartTime().isAfter(examQueryDTO.getStartTime()) &&
                                examVO.getEndTime().isBefore(examQueryDTO.getEndTime()))
                        .collect(Collectors.toList());
            }

        }
        if (CollectionUtil.isEmpty(examVOList)) {
            return TableDataInfo.empty();
        }
        // 这个函数是将对应类型的竞赛列表数据如果用户报名了就会设置为已报名 当然这只会在登录状态下发送
        assembleExamVOList(examVOList);
        return TableDataInfo.success(examVOList, total);
    }

    @Override
    public TableDataInfo rankList(ExamRankDTO examRankDTO) {
        // 从redis中去获取   竞赛列表的数据
        Long total = examCacheManager.getRankListSize(examRankDTO.getExamId());
        List<ExamRankVO> examRankVOList;
        if (total == null || total <= 0) {
            PageHelper.startPage(examRankDTO.getPageNum(), examRankDTO.getPageSize());
            examRankVOList = userExamMapper.selectExamRankList(examRankDTO.getExamId());
            examCacheManager.refreshExamRankCache(examRankDTO.getExamId());
            total = new PageInfo<>(examRankVOList).getTotal();
        } else {
            examRankVOList = examCacheManager.getExamRankList(examRankDTO);
        }
        if (CollectionUtil.isEmpty(examRankVOList)) {
            return TableDataInfo.empty();
        }
        assembleExamRankVOList(examRankVOList);
        return TableDataInfo.success(examRankVOList, total);
    }

    @Override
    public String getFirstQuestion(Long examId) {
        checkAndRefresh(examId);
        return examCacheManager.getFirstQuestion(examId).toString();
    }

    @Override
    public String preQuestion(Long examId, Long questionId) {
        checkAndRefresh(examId);

        return examCacheManager.preQuestion(examId, questionId).toString();
    }

    @Override
    public String nextQuestion(Long examId, Long questionId) {
        checkAndRefresh(examId);

        return examCacheManager.nextQuestion(examId, questionId).toString();
    }


    private void checkAndRefresh(Long examId) {
        Long listSize = examCacheManager.getExamQuestionListSize(examId);
        if (listSize <= 0 || listSize == null) {
            examCacheManager.refreshExamQuestionCache(examId);
        }
    }

    private void assembleExamVOList(List<ExamVO> examVOList) {
        Long userId = ThreadLocalUtil.get(Constants.USER_ID, Long.class);
        List<Long> userExamIdList = examCacheManager.getAllUserExamList(userId);
        if (CollectionUtil.isEmpty(userExamIdList)) {
            return;
        }
        for (ExamVO examVO : examVOList) {
            if (userExamIdList.contains(examVO.getExamId())) {
                examVO.setEnter(true);
            }
        }
    }

    private void assembleExamRankVOList(List<ExamRankVO> examRankVOList) {
        if (CollectionUtil.isEmpty(examRankVOList)) {
            return;
        }

        for (ExamRankVO examRankVO : examRankVOList) {
            Long userId = examRankVO.getUserId();
            UserVO user = userCacheManager.getUserById(userId);
            examRankVO.setNickName(user.getNickName());
        }
    }
}
