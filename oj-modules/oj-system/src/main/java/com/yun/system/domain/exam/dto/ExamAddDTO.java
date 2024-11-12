package com.yun.system.domain.exam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yun
 * @date 2024/11/11 9:42
 * @desciption: 添加竞赛时要传递给后端的参数类
 */
@Getter
@Setter
public class ExamAddDTO {
    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "开始时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @NotNull(message = "结束时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 这个注解能够自动转换字符串和日期
    private LocalDateTime endTime;
}
