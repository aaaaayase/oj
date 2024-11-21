package com.yun.system.manager;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yun.common.core.constants.CacheConstants;
import com.yun.common.core.enums.ResultCode;
import com.yun.common.redis.service.RedisService;
import com.yun.common.security.exception.ServiceException;
import com.yun.system.domain.question.Question;
import com.yun.system.mapper.question.IQuestionMapper;
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

    public void addCache(Long questionId) {
        redisService.leftPushForList(CacheConstants.QUESTION_lIST, questionId);
    }

    public void deleteCache(Long questionId) {
        redisService.removeForList(CacheConstants.QUESTION_lIST, questionId);
    }
}
