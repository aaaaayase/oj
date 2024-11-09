package com.yun.system.domain.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yun.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/8 20:43
 * @desciption: 题目实体类
 */
@TableName("tb_question")
@Setter
@Getter
public class Question extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long questionId;

    private String title;

    private Integer difficulty;

    private Long timeLimit;

    private Long spaceLimit;

    private String content;

    private String questionCase;

    private String defaultCode;

    private String mainFunc;

}
