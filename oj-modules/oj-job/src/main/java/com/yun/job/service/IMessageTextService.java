package com.yun.job.service;

import com.yun.job.domain.message.MessageText;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/22 10:21
 * @desciption:
 */
public interface IMessageTextService {
    boolean batchInsert(List<MessageText> messageTextList);
}
