package com.yun.system.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yun.common.core.constants.Constants;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.common.core.enums.ResultCode;
import com.yun.common.security.exception.ServiceException;
import com.yun.system.domain.question.Question;
import com.yun.system.domain.question.dto.QuestionAddDTO;
import com.yun.system.domain.question.dto.QuestionEditDTO;
import com.yun.system.domain.question.dto.QuestionQueryDTO;
import com.yun.system.domain.question.vo.QuestionDetailVO;
import com.yun.system.domain.question.vo.QuestionVO;
import com.yun.system.mapper.question.IQuestionMapper;
import com.yun.system.service.question.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yun
 * @date 2024/11/8 22:12
 * @desciption: 问题相关业务逻辑
 */
@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private IQuestionMapper questionMapper;

    @Override
    public List<QuestionVO> list(QuestionQueryDTO questionQueryDTO) {
        String excludeIdStr = questionQueryDTO.getExcludeIdStr();
        if (StrUtil.isNotEmpty(excludeIdStr)) {
            String[] excludeIdArr = excludeIdStr.split(Constants.SPLIT_SEM);
            Set<Long> excludeIdSet = Arrays
                    .stream(excludeIdArr)
                    .map(Long::valueOf)
                    .collect(Collectors.toSet());
            questionQueryDTO.setExcludeIdSet(excludeIdSet);
        }
        // 这一步按照我的理解是用于初始化你要分页的页码和题目数
        PageHelper.startPage(questionQueryDTO.getPageNum(), questionQueryDTO.getPageSize());
        // 当你查询出满足条件的结果时 pagehelper就会自动帮你给分
        return questionMapper.selectQuestionList(questionQueryDTO);

//        if (CollectionUtil.isEmpty(questionVOList)) {
//            return TableDataInfo.empty();
//        }
//
//        // pagehelper还会提前做好满足条件结果的总数的计数
//        int total = (int) new PageInfo<>(questionVOList).getTotal();
//        return TableDataInfo.success(questionVOList, total);
    }

    @Override
    public int add(QuestionAddDTO questionAddDTO) {
        // 添加之前需要判断当前的题目是否已经存在 这里的标准就定为标题需要不一样
        List<Question> questionList = questionMapper.selectList(new LambdaQueryWrapper<Question>().eq(Question::getTitle, questionAddDTO.getTitle()));
        if (!CollectionUtil.isEmpty(questionList)) {
            throw new ServiceException(ResultCode.FAILED_ALREADY_EXISTS);
        }
        Question question = new Question();
        BeanUtil.copyProperties(questionAddDTO, question);
        return questionMapper.insert(question);
    }

    @Override
    public QuestionDetailVO detail(Long questionId) {
        Question question = questionMapper.selectById(questionId);
        if (question == null) {
            throw new ServiceException(ResultCode.FAILED_NOT_EXISTS);
        }
        QuestionDetailVO questionDetailVO = new QuestionDetailVO();
        BeanUtil.copyProperties(question, questionDetailVO);
        return questionDetailVO;
    }

    @Override
    public int edit(QuestionEditDTO questionEditDTO) {
        Question oldQuestion = questionMapper.selectById(questionEditDTO.getQuestionId());
        if (oldQuestion == null) {
            throw new ServiceException(ResultCode.FAILED_NOT_EXISTS);
        }
        oldQuestion.setQuestionCase(questionEditDTO.getQuestionCase());
        oldQuestion.setContent(questionEditDTO.getContent());
        oldQuestion.setDifficulty(questionEditDTO.getDifficulty());
        oldQuestion.setMainFuc(questionEditDTO.getMainFuc());
        oldQuestion.setTitle(questionEditDTO.getTitle());
        oldQuestion.setDefaultCode(questionEditDTO.getDefaultCode());
        oldQuestion.setSpaceLimit(questionEditDTO.getSpaceLimit());
        oldQuestion.setTimeLimit(questionEditDTO.getTimeLimit());
        return questionMapper.updateById(oldQuestion);
    }

    @Override
    public int delete(Long questionId) {
        Question question = questionMapper.selectById(questionId);
        if (question == null) {
            throw new ServiceException(ResultCode.FAILED_NOT_EXISTS);
        }
        return questionMapper.deleteById(questionId);
    }
}
