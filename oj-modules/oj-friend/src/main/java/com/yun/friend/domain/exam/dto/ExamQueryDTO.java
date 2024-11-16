package com.yun.friend.domain.exam.dto;

import com.yun.common.core.domain.dto.PageQueryDTO;
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

    private Integer type; // 0代表未完赛 1代表历史
}
