package com.yun.friend.manager;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yun.common.core.constants.CacheConstants;
import com.yun.common.core.enums.ResultCode;
import com.yun.common.redis.service.RedisService;
import com.yun.common.security.exception.ServiceException;
import com.yun.friend.domain.question.Question;
import com.yun.friend.domain.question.es.QuestionES;
import com.yun.friend.mapper.question.IQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/19 9:41
 * @desciption:
 */
@Component
public class QuestionCacheManager {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IQuestionMapper questionMapper;

    public Long getListSize() {
        return redisService.getListSize(CacheConstants.QUESTION_lIST);
    }

    public void refreshCache() {
        List<Question> questionList = questionMapper.selectList(new LambdaQueryWrapper<Question>().select(Question::getQuestionId).orderByDesc(Question::getCreateTime));
        if (CollectionUtil.isEmpty(questionList)) {
            return;
        }
        List<Long> questionIdList = questionList.stream().map(Question::getQuestionId).toList();
        redisService.rightPushAll(CacheConstants.QUESTION_lIST, questionIdList);
    }

    public Long preQuestion(Long questionId) {
        Long index = redisService.indexOfForList(CacheConstants.QUESTION_lIST, questionId);
        if (index == 0) {
            throw new ServiceException(ResultCode.FAILED_FIRST_QUESTION);
        }

        return redisService.indexForList(CacheConstants.QUESTION_lIST, index - 1, Long.class);
    }

    public Long nextQuestion(Long questionId) {
        Long index = redisService.indexOfForList(CacheConstants.QUESTION_lIST, questionId);
        long lastIndex = getListSize() - 1;
        if (index == lastIndex) {
            throw new ServiceException(ResultCode.FAILED_FIRST_QUESTION);
        }

        return redisService.indexForList(CacheConstants.QUESTION_lIST, index + 1, Long.class);

    }

    public Long getHostListSize() {
        return redisService.getListSize(CacheConstants.QUESTION_HOST_LIST);
    }

    public List<Long> getHostList() {
        return redisService.getCacheListByRange(CacheConstants.QUESTION_HOST_LIST,
                CacheConstants.DEFAULT_START, CacheConstants.DEFAULT_END, Long.class);
    }

    public void refreshHotQuestionList(List<Long> hotQuestionIdList) {
        if (CollectionUtil.isEmpty(hotQuestionIdList)) {
            return;
        }
        redisService.rightPushAll(CacheConstants.QUESTION_HOST_LIST, hotQuestionIdList);
    }
}
