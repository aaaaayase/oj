package com.yun.job.service;

import com.yun.job.domain.message.Message;
import com.yun.job.domain.message.MessageText;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/22 10:21
 * @desciption:
 */
public interface IMessageService {
    boolean batchInsert(List<Message> messageList);
}
