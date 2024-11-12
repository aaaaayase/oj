package com.yun.system.domain.exam.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;

/**
 * @author yun
 * @date 2024/11/11 11:44
 * @desciption: 在竞赛中添加题目的传递给后端的参数类
 */
@Setter
@Getter
public class ExamQuestionAddDTO {
    private Long examId;
    private LinkedHashSet<Long> questionIdSet;
}
