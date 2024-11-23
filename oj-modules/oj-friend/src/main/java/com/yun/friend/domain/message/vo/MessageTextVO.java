package com.yun.friend.domain.message.vo;

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
    private Long textId;

    private String messageTitle;

    private String messageContent;
}
