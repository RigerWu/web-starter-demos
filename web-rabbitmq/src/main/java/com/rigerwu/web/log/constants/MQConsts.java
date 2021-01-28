package com.rigerwu.web.log.constants;

/**
 * created by riger on 2021/1/25
 */
public interface MQConsts {

    String DIRECT_EXCHANGE = "direct-exchange";
    String FANOUT_EXCHANGE = "fanout-exchange";
    String TOPIC_EXCHANGE = "topic-exchange";


    String QUEUE1 = "queue1";
    String QUEUE2 = "queue2";
    String QUEUE3 = "queue3";

    /**
     * * 一个占位符 #一个或者多个占位符
     */
    String QUEUE_ROUTING_KEY1 = "*.queue";
    String QUEUE_ROUTING_KEY2 = "#.queue";
    String QUEUE_ROUTING_KEY3 = "com.#";


    String DURABLE_EXCHANGE = "durable-exchange";
    String DURABLE_QUEUE = "queue4";

    String DELAYED_QUEUE = "delayed.queue";
    String DELAYED_ECCHANGE = "delayed.exchange";

}
