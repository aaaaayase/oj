package com.yun.job.domain.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yun.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/17 9:45
 * @desciption: 用户与竞赛关系实体
 */
@Getter
@Setter
@TableName("tb_user_exam")
public class UserExam extends BaseEntity {
    @TableId(value = "USER_EXAM_ID", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private String userExamId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long examId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private Integer score;

    private Integer examRank;
}
