package com.yun.friend.service.exam;

import com.yun.common.core.domain.TableDataInfo;
import com.yun.friend.domain.exam.dto.ExamQueryDTO;
import com.yun.friend.domain.exam.dto.ExamRankDTO;
import com.yun.friend.domain.exam.vo.ExamVO;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/15 15:38
 * @desciption: 竞赛相关接口
 */
public interface IExamService {
    List<ExamVO> list(ExamQueryDTO examQueryDTO);

    TableDataInfo redisList(ExamQueryDTO examQueryDTO);

    TableDataInfo rankList(ExamRankDTO examRankDTO);

    String getFirstQuestion(Long examId);

    String preQuestion(Long examId, Long questionId);

    String nextQuestion(Long examId, Long questionId);

}
