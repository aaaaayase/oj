package com.yun.system.domain.question.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yun
 * @date 2024/11/8 22:30
 * @desciption: 问题传给前端的数据
 */
@Getter
@Setter
public class QuestionVO {
    private Long questionId;

    private String title;

    private Integer difficulty;

    // 这里的值是创建的管理员的昵称
    private String createName;

    private LocalDateTime createTime;
}
