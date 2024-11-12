package com.yun.system.service.exam;

import com.yun.system.domain.exam.dto.ExamAddDTO;
import com.yun.system.domain.exam.dto.ExamEditDTO;
import com.yun.system.domain.exam.dto.ExamQueryDTO;
import com.yun.system.domain.exam.dto.ExamQuestionAddDTO;
import com.yun.system.domain.exam.vo.ExamDetailVO;
import com.yun.system.domain.exam.vo.ExamVO;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/10 19:32
 * @desciption: 竞赛相关接口
 */
public interface IExamService {
    List<ExamVO> list(ExamQueryDTO examQueryDTO);

    String add(ExamAddDTO examAddDTO);

    boolean questionAdd(ExamQuestionAddDTO examQuestionAddDTO);

    ExamDetailVO detail(Long examId);

    int edit(ExamEditDTO examEditDTO);

    int questionDelete(Long examId, Long questionId);

    int delete(Long examId);

    int publish(Long examId);

    int cancelPublish(Long examId);
}
