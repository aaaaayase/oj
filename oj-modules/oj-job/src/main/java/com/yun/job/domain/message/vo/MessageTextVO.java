package com.yun.job.domain.message.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yun.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/22 10:00
 * @desciption:
 */
@Getter
@Setter
public class MessageTextVO {

    private String messageTitle;

    private String messageContent;
}
