package com.yun.common.rabbit;

import com.yun.common.core.constants.RabbitMQConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yun
 * @date 2024/11/20 12:16
 * @desciption: rabbitmq配置
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue workQueue() {
        return new Queue(RabbitMQConstants.OJ_WORK_QUEUE, true);
    }
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}