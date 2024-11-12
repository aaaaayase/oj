package com.yun.system.service.exam.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.yun.common.core.enums.ResultCode;
import com.yun.common.security.exception.ServiceException;
import com.yun.system.domain.exam.Exam;
import com.yun.system.domain.exam.ExamQuestion;
import com.yun.system.domain.exam.dto.ExamAddDTO;
import com.yun.system.domain.exam.dto.ExamEditDTO;
import com.yun.system.domain.exam.dto.ExamQueryDTO;
import com.yun.system.domain.exam.dto.ExamQuestionAddDTO;
import com.yun.system.domain.exam.vo.ExamDetailVO;
import com.yun.system.domain.exam.vo.ExamVO;
import com.yun.system.domain.question.Question;
import com.yun.system.domain.question.vo.QuestionVO;
import com.yun.system.mapper.exam.IExamMapper;
import com.yun.system.mapper.exam.IExamQuestionMapper;
import com.yun.system.mapper.question.IQuestionMapper;
import com.yun.system.service.exam.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yun
 * @date 2024/11/10 19:38
 * @desciption: 竞赛相关业务逻辑
 */
@Service
public class ExamServiceImpl extends ServiceImpl<IExamQuestionMapper, ExamQuestion> implements IExamService {
    // 这里继承的是mabatis plus扩展的类 为了使用其中的saveBatch方法批量存储
    @Autowired
    private IExamMapper examMapper;

    @Autowired
    private IQuestionMapper questionMapper;

    @Autowired
    private IExamQuestionMapper examQuestionMapper;

    @Override
    public List<ExamVO> list(ExamQueryDTO examQueryDTO) {
        // 这一步按照我的理解是用于初始化你要分页的页码和题目数
        PageHelper.startPage(examQueryDTO.getPageNum(), examQueryDTO.getPageSize());
        // 当你查询出满足条件的结果时 pagehelper就会自动帮你给分
        return examMapper.selectExamList(examQueryDTO);
    }

    @Override
    public String add(ExamAddDTO examAddDTO) {
        checkExamSaveParams(examAddDTO, null);
        Exam exam = new Exam();
        BeanUtil.copyProperties(examAddDTO, exam);
        examMapper.insert(exam);
        return exam.getExamId().toString();
    }

    @Override
    public boolean questionAdd(ExamQuestionAddDTO examQuestionAddDTO) {
        Exam exam = getExam(examQuestionAddDTO.getExamId());
        LinkedHashSet<Long> questionIdSet = examQuestionAddDTO.getQuestionIdSet();
        if (CollectionUtil.isEmpty(questionIdSet)) {
            return true;
        }

        List<Question> questionList = questionMapper.selectBatchIds(questionIdSet);
        if (CollectionUtil.isEmpty(questionList) || questionList.size() < questionIdSet.size()) {
            throw new ServiceException(ResultCode.EXAM_QUESTION_NOT_EXISTS);
        }

        return saveExamQuestion(questionIdSet, exam);
    }

    @Override
    public ExamDetailVO detail(Long examId) {
        // 获取竞赛对象
        Exam exam = getExam(examId);
        // 新建传递参数对象
        ExamDetailVO examDetailVO = new ExamDetailVO();
        // 传参数给参数对象
        BeanUtil.copyProperties(exam, examDetailVO);
        // 去获取当前竞赛下的题目id号列表
        List<ExamQuestion> examQuestionList = examQuestionMapper.selectList(new LambdaQueryWrapper<ExamQuestion>().select(ExamQuestion::getQuestionId).eq(ExamQuestion::getExamId, examId).orderByAsc(ExamQuestion::getQuestionOrder));
        if (CollectionUtil.isEmpty(examQuestionList)) {
            return examDetailVO;
        }
        // 获取单纯包含题目号的列表
        List<Long> questionIdList = examQuestionList.stream().map(ExamQuestion::getQuestionId).toList();
        // 根据id列表去查找题目的详细信息
        List<Question> questionList = questionMapper.selectList(new LambdaQueryWrapper<Question>().select(Question::getQuestionId, Question::getTitle, Question::getDifficulty).in(Question::getQuestionId, questionIdList));
        List<QuestionVO> questionVOList = BeanUtil.copyToList(questionList, QuestionVO.class);
        examDetailVO.setExamQuestionList(questionVOList);
        return examDetailVO;
    }

    @Override
    public int edit(ExamEditDTO examEditDTO) {
        // 先进行竞赛查找 查不到就不需要进行后续操作了
        Exam exam = getExam(examEditDTO.getExamId());
        checkExamSaveParams(examEditDTO, examEditDTO.getExamId());
        exam.setTitle(examEditDTO.getTitle());
        exam.setEndTime(examEditDTO.getEndTime());
        exam.setStartTime(examEditDTO.getStartTime());
        return examMapper.updateById(exam);
    }

    private void checkExamSaveParams(ExamAddDTO examSaveDTO, Long examId) {
        // 标题不能重复
        // 竞赛开始时间不能早于当前时间
        // 竞赛结束时间不能早于开始时间
        List<Exam> examList = examMapper.selectList(new LambdaQueryWrapper<Exam>().ne(examId != null, Exam::getExamId, examId).eq(Exam::getTitle, examSaveDTO.getTitle()));
        if (CollectionUtil.isNotEmpty(examList)) {
            throw new ServiceException(ResultCode.FAILED_ALREADY_EXISTS);
        }
        if (examSaveDTO.getStartTime().isBefore(LocalDateTime.now())) {
            throw new ServiceException(ResultCode.EXAM_START_TIME_BEFORE_CURRENT_TIME);
        }
        if (examSaveDTO.getStartTime().isAfter(examSaveDTO.getEndTime())) {
            throw new ServiceException(ResultCode.EXAM_START_TIME_AFTER_END_TIME);
        }
    }

    private boolean saveExamQuestion(LinkedHashSet<Long> questionIdSet, Exam exam) {
        int num = 1;
        List<ExamQuestion> examQuestionList = new ArrayList<>();
        for (Long questionId : questionIdSet) {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setQuestionId(questionId);
            examQuestion.setExamId(exam.getExamId());
            examQuestion.setQuestionOrder(num++);
            examQuestionList.add(examQuestion);
        }

        return saveBatch(examQuestionList);
    }

    private Exam getExam(Long examId) {
        Exam exam = examMapper.selectById(examId);
        if (exam == null) {
            throw new ServiceException(ResultCode.EXAM_NOT_EXISTS);
        }
        return exam;
    }
}
