package com.yun.system.domain.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yun
 * @date 2024/11/10 19:41
 * @desciption: 返回前端参数
 */
@Setter
@Getter
public class ExamVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long examId;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    // 是否已经发布
    private Integer status;

    private String createName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}