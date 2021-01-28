package com.rigerwu.web.log.config;

import cn.hutool.core.map.MapUtil;
import com.rigerwu.web.log.constants.MQConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * created by riger on 2021/1/25
 */
@Configuration
@Slf4j
public class MQConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 消息使用Json转换
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @PostConstruct
    public void init() {
        DirectExchange direct = new DirectExchange(MQConsts.DIRECT_EXCHANGE);
        FanoutExchange fanout = new FanoutExchange(MQConsts.FANOUT_EXCHANGE);
        TopicExchange topic = new TopicExchange(MQConsts.TOPIC_EXCHANGE);

        amqpAdmin.declareExchange(direct);
        amqpAdmin.declareExchange(fanout);
        amqpAdmin.declareExchange(topic);
        Queue queue1 = new Queue(MQConsts.QUEUE1);
        Queue queue2 = new Queue(MQConsts.QUEUE2);
        Queue queue3 = new Queue(MQConsts.QUEUE3);
        amqpAdmin.declareQueue(queue1);
        amqpAdmin.declareQueue(queue2);
        amqpAdmin.declareQueue(queue3);
        // direct 这里偷懒直接用队列名作为 BindingKey
        amqpAdmin.declareBinding(BindingBuilder.bind(queue1).to(direct).with(MQConsts.QUEUE1));
        amqpAdmin.declareBinding(BindingBuilder.bind(queue2).to(direct).with(MQConsts.QUEUE2));
        amqpAdmin.declareBinding(BindingBuilder.bind(queue3).to(direct).with(MQConsts.QUEUE3));
        // fanout 可以看到 fanout exchange 绑定不需要 bindingkey
        amqpAdmin.declareBinding(BindingBuilder.bind(queue1).to(fanout));
        amqpAdmin.declareBinding(BindingBuilder.bind(queue2).to(fanout));
        amqpAdmin.declareBinding(BindingBuilder.bind(queue3).to(fanout));
        // topic
        amqpAdmin.declareBinding(BindingBuilder.bind(queue1).to(topic).with(MQConsts.QUEUE_ROUTING_KEY1));
        amqpAdmin.declareBinding(BindingBuilder.bind(queue2).to(topic).with(MQConsts.QUEUE_ROUTING_KEY2));
        amqpAdmin.declareBinding(BindingBuilder.bind(queue3).to(topic).with(MQConsts.QUEUE_ROUTING_KEY3));

        /*
         * 生产者回调
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息发送到broker: " + correlationData);
            } else {
                log.error("消息发送失败:" + cause);
            }
        });
        rabbitTemplate.setReturnsCallback(returned -> log.info("消息\"{}\"发送到队列失败:{}",
                new String(returned.getMessage().getBody()),
                returned.getReplyText()));

        /*
         * 持久化测试
         */
        FanoutExchange exchange = new FanoutExchange(MQConsts.DURABLE_EXCHANGE);
        Queue queue = new Queue(MQConsts.DURABLE_QUEUE);
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange));

        /*
         * 延迟队列
         *
         */
        Queue delayedQueue = new Queue(MQConsts.DELAYED_QUEUE);
        // 根据官方文档,我们这样声明一个Exchange
        Map<String, Object> args = MapUtil.newHashMap();
        args.put("x-delayed-type", "direct");
        CustomExchange customExchange = new CustomExchange(MQConsts.DELAYED_ECCHANGE, "x-delayed-message", true, false, args);
        amqpAdmin.declareQueue(delayedQueue);
        amqpAdmin.declareExchange(customExchange);
        // 这里路由键我们就用延迟队列的名字
        amqpAdmin.declareBinding(BindingBuilder.bind(delayedQueue).to(customExchange).with(MQConsts.DELAYED_QUEUE).noargs());

    }

}
