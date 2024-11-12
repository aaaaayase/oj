package com.yun.system.domain.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yun.system.domain.question.Question;
import com.yun.system.domain.question.vo.QuestionVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yun
 * @date 2024/11/11 18:51
 * @desciption: 获取的竞赛详情返回给前端的参数类
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamDetailVO {

    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private List<QuestionVO> examQuestionList;
}
