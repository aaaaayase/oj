package com.yun.judge.rabbit;

import com.yun.api.domain.dto.JudgeSubmitDTO;
import com.yun.common.core.constants.RabbitMQConstants;
import com.yun.judge.service.IJudgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yun
 * @date 2024/11/20 12:36
 * @desciption: 消费者
 */
@Slf4j
@Component
public class JudgeConsumer {
    @Autowired
    private IJudgeService judgeService;
    @RabbitListener(queues = RabbitMQConstants.OJ_WORK_QUEUE)
    public void consume(JudgeSubmitDTO judgeSubmitDTO) {
        log.info("收到消息为: {}", judgeSubmitDTO);
        judgeService.doJudgeJavaCode(judgeSubmitDTO);
    }
}
