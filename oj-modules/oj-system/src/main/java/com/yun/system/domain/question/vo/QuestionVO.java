package com.yun.system.domain.question.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionVO {

    // 前端在接收较长id时会发生截断 因此需要去转为字符串 雪花算法生成的id太长了
    @JsonSerialize(using = ToStringSerializer.class)
    private Long questionId;

    private String title;

    private Integer difficulty;

    // 这里的值是创建的管理员的昵称
    private String createName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
