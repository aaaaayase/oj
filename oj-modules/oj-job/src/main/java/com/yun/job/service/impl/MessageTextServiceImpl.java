package com.yun.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.job.domain.message.MessageText;
import com.yun.job.mapper.exam.IExamMapper;
import com.yun.job.mapper.message.IMessageTextMapper;
import com.yun.job.service.IMessageTextService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/22 10:22
 * @desciption:
 */
@Service
public class MessageTextServiceImpl extends ServiceImpl<IMessageTextMapper, MessageText> implements IMessageTextService {

    @Override
    public boolean batchInsert(List<MessageText> messageTextList) {
        return saveBatch(messageTextList);
    }

}
