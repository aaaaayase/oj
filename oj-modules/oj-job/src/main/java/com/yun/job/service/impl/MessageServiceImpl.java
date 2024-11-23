package com.yun.job.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.job.domain.message.Message;
import com.yun.job.mapper.message.IMessageMapper;
import com.yun.job.service.IMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/22 10:22
 * @desciption:
 */
@Service
public class MessageServiceImpl extends ServiceImpl<IMessageMapper, Message> implements IMessageService {

    @Override
    public boolean batchInsert(List<Message> messageList) {
        return saveBatch(messageList);
    }

}
