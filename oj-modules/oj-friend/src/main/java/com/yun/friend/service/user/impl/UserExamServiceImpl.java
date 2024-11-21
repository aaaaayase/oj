package com.yun.friend.service.user.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yun.common.core.constants.Constants;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.common.core.enums.ExamListType;
import com.yun.common.core.enums.ResultCode;
import com.yun.common.core.utils.ThreadLocalUtil;
import com.yun.common.security.exception.ServiceException;
import com.yun.common.security.service.TokenService;
import com.yun.friend.domain.exam.Exam;
import com.yun.friend.domain.exam.dto.ExamQueryDTO;
import com.yun.friend.domain.exam.vo.ExamVO;
import com.yun.friend.domain.user.UserExam;
import com.yun.friend.manager.ExamCacheManager;
import com.yun.friend.mapper.exam.IExamMapper;
import com.yun.friend.mapper.user.IUserExamMapper;
import com.yun.friend.service.user.IUserExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yun
 * @date 2024/11/17 9:59
 * @desciption:
 */

@Service
public class UserExamServiceImpl implements IUserExamService {

    @Autowired
    private IExamMapper examMapper;

    @Autowired
    private IUserExamMapper userExamMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ExamCacheManager examCacheManager;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public int enter(String token, Long examId) {
        Exam exam = examMapper.selectById(examId);
        if (exam == null) {
            throw new ServiceException(ResultCode.FAILED_NOT_EXISTS);
        }
        if (exam.getStartTime().isBefore(LocalDateTime.now())) {
            throw new ServiceException(ResultCode.EXAM_STARTED);
        }
//        Long userId = tokenService.getUserId(token, secret);
        Long userId = ThreadLocalUtil.get(Constants.USER_ID, Long.class);
        UserExam userExam = userExamMapper.selectOne(new LambdaQueryWrapper<UserExam>()
                .eq(UserExam::getExamId, examId)
                .eq(UserExam::getUserId, userId));
        if (userExam != null) {
            throw new ServiceException(ResultCode.USER_EXAM_HAS_ENTER);
        }
        examCacheManager.addUserExamCache(userId, examId);
        userExam = new UserExam();
        userExam.setExamId(examId);
        userExam.setUserId(userId);
        return userExamMapper.insert(userExam);
    }

    // 先查询缓存 如果缓存能查询到直接返回 否则去数据库中查询并且数据库数据同步到缓存
    @Override
    public TableDataInfo list(ExamQueryDTO examQueryDTO) {
        // 从redis中去获取   竞赛列表的数据
        Long userId = ThreadLocalUtil.get(Constants.USER_ID, Long.class);
        Long total = examCacheManager.getListSize(ExamListType.USER_EXAM_lIST.getValue(), userId);
        List<ExamVO> examVOList;
        if (total == null || total <= 0) {
            // 从数据库中查询并且同步到缓存
            PageHelper.startPage(examQueryDTO.getPageNum(), examQueryDTO.getPageSize());
            examVOList = userExamMapper.selectUserExamList(userId);
            examCacheManager.refreshCache(ExamListType.USER_EXAM_lIST.getValue(),userId);
            total = new PageInfo<>(examVOList).getTotal();
        } else {
            examQueryDTO.setType(ExamListType.USER_EXAM_lIST.getValue());
            examVOList = examCacheManager.getExamVOList(examQueryDTO,userId);
            total = examCacheManager.getListSize(examQueryDTO.getType(),userId);
        }
        if (CollectionUtil.isEmpty(examVOList)) {
            return TableDataInfo.empty();
        }

        return TableDataInfo.success(examVOList, total);
    }
}
