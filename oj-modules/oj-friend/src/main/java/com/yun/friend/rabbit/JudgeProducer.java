package com.yun.friend.rabbit;

import com.yun.api.domain.dto.JudgeSubmitDTO;
import com.yun.common.core.constants.RabbitMQConstants;
import com.yun.common.core.enums.ResultCode;
import com.yun.common.security.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yun
 * @date 2024/11/20 12:31
 * @desciption: 生产者
 */
@Component
@Slf4j
public class JudgeProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void produceMsg(JudgeSubmitDTO judgeSubmitDTO) {
        log.info(judgeSubmitDTO.toString());
        try {
            rabbitTemplate.convertAndSend(RabbitMQConstants.OJ_WORK_QUEUE,
                    judgeSubmitDTO);
        } catch (Exception e) {
            log.error("⽣产者发送消息异常", e);
            throw new ServiceException(ResultCode.FAILED_RABBIT_PRODUCE);
        }
    }
}