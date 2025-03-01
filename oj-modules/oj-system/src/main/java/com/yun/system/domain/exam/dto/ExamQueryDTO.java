package com.yun.system.domain.exam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yun.common.core.domain.dto.PageQueryDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yun
 * @date 2024/11/10 19:34
 * @desciption: 前端传递参数类
 */
@Setter
@Getter
public class ExamQueryDTO extends PageQueryDTO {

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
