package com.yun.friend.domain.message;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yun.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/22 10:03
 * @desciption: 消息实体类
 */
@TableName("tb_message")
@Getter
@Setter
public class Message extends BaseEntity {

    @TableId(value = "MESSAGE_ID", type = IdType.ASSIGN_ID)
    private Long messageId;

    private Long textId;

    private Long sendId;

    private Long recId;
}
