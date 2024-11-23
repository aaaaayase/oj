package com.yun.job.domain.message;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yun.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/22 10:00
 * @desciption: 消息内容实体类
 */
@TableName("tb_message_text")
@Getter
@Setter
public class MessageText extends BaseEntity {

    @TableId(value = "TEXT_ID", type = IdType.ASSIGN_ID)
    private Long textId;

    private String messageTitle;

    private String messageContent;
}
