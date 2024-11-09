package com.yun.system.service.question.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.system.domain.question.dto.QuestionQueryDTO;
import com.yun.system.domain.question.vo.QuestionVO;
import com.yun.system.mapper.question.IQuestionMapper;
import com.yun.system.service.question.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
