package com.rigerwu.web.rabbitmq.service;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.rigerwu.web.rabbitmq.constants.MQConsts;
import com.rigerwu.web.rabbitmq.entity.MsgBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * created by riger on 2021/1/25
 */
@Service
@Slf4j
public class QueueListener {

    @RabbitListener(queues = MQConsts.QUEUE1)
    @RabbitHandler
    public void receiveQueue1(MsgBean msgBean) {
        log.info("queue1 receive msg -> :" + JSONUtil.toJsonStr(msgBean));
    }

    @RabbitListener(queues = MQConsts.QUEUE2)
    @RabbitHandler
    public void receiveQueue2(MsgBean msgBean) {
        log.info("queue2 receive msg -> :" + JSONUtil.toJsonStr(msgBean));
    }

//    @RabbitListener(queues = MQConsts.QUEUE3)
//    @RabbitHandler
    public void receiveQueue3(MsgBean msgBean) {
        log.info("queue3 receive msg -> :" + JSONUtil.toJsonStr(msgBean));
    }

    @RabbitListener(queues = MQConsts.QUEUE3)
    @RabbitHandler
    public void receiveQueue3ManualAck(MsgBean msgBean, Message message, Channel channel) {
        // deliveryTag 在通道内顺序自增
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // 消费消息
            log.info("queue3 receive msg -> :" + JSONUtil.toJsonStr(msgBean));
            // 通知MQ已经成功消费,可以ack, false 表示不批量
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 处理失败,重新放回MQ
                channel.basicRecover();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    @RabbitListener(queues = MQConsts.DELAYED_QUEUE)
    @RabbitHandler
    public void receiveDelayedQueue(MsgBean msgBean) {
        log.info("delayed queue receive msg -> :" + JSONUtil.toJsonStr(msgBean));
        log.info("receive time:{}", System.currentTimeMillis());
    }
}


