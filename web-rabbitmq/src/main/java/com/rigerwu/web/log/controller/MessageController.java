package com.rigerwu.web.log.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.rigerwu.web.log.constants.MQConsts;
import com.rigerwu.web.log.entity.MsgBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by riger on 2021/1/12
 */
@RestController
@Slf4j
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/direct")
    public String directMessage() {
        rabbitTemplate.convertAndSend(MQConsts.DIRECT_EXCHANGE, "queue1", new MsgBean("direct message 1"));
        rabbitTemplate.convertAndSend(MQConsts.DIRECT_EXCHANGE, "queue2", new MsgBean("direct message 2"));
        rabbitTemplate.convertAndSend(MQConsts.DIRECT_EXCHANGE, "queue3", new MsgBean("direct message 3"));
        rabbitTemplate.convertAndSend(MQConsts.DIRECT_EXCHANGE, "queue4", new MsgBean("direct message 4"));
        return "OK";
    }


    @GetMapping("/fanout")
    public String fanoutMessage() {
        rabbitTemplate.convertAndSend(MQConsts.FANOUT_EXCHANGE, "xxx", new MsgBean("fanout message"));
        return "OK";
    }

    @GetMapping("/topic")
    public String topicMessage() {

        log.info("queue1 bindingkey: *.queue");
        log.info("queue2 bindingkey: #.queue");
        log.info("queue3 bindingkey: com.#");

        String routingKey1 = "test.queue";
        String routingKey2 = "com.riger.queue";
        String routingKey3 = "com.queue";
        rabbitTemplate.convertAndSend(MQConsts.TOPIC_EXCHANGE, routingKey1,
                new MsgBean("topic message routingkey: "+ routingKey1));
        ThreadUtil.sleep(1000);
        log.info("----------------------------------------------");
        rabbitTemplate.convertAndSend(MQConsts.TOPIC_EXCHANGE, routingKey2,
                new MsgBean("topic message routingkey: "+ routingKey2));
        ThreadUtil.sleep(1000);
        log.info("----------------------------------------------");
        rabbitTemplate.convertAndSend(MQConsts.TOPIC_EXCHANGE, routingKey3,
                new MsgBean("topic message routingkey: "+ routingKey3));
        return "OK";
    }


    @GetMapping("/direct-confrim")
    public String directConfirm() {

        CorrelationData data = new CorrelationData();
        log.info("准备发送消息1-id: " + data.getId());
        rabbitTemplate.convertAndSend(MQConsts.DIRECT_EXCHANGE, MQConsts.QUEUE1, new MsgBean("direct message1"), data);
        ThreadUtil.sleep(1000);
        log.info("----------------------------------------------");
        data = new CorrelationData();
        log.info("准备发送消息2-id: " + data.getId());
        rabbitTemplate.convertAndSend(MQConsts.DIRECT_EXCHANGE, "abcde", new MsgBean("direct message2"), data);
        return "OK";
    }

    @GetMapping("/ack")
    public String consumerMaualAck() {
        rabbitTemplate.convertAndSend(MQConsts.DIRECT_EXCHANGE, "queue3", new MsgBean("manual ack"));
        return "OK";
    }

    @GetMapping("/delay")
    public String delayQueue() {
        log.info("send time:{}", System.currentTimeMillis());
        rabbitTemplate.convertAndSend(MQConsts.DELAYED_ECCHANGE, MQConsts.DELAYED_QUEUE, new MsgBean("delay queue"), message -> {
            message.getMessageProperties().setHeader("x-delay", 5000);
            return message;
        });
        return "OK";
    }

}
