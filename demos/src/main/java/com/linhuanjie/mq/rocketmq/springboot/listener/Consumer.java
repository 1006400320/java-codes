package com.linhuanjie.mq.rocketmq.springboot.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@RocketMQMessageListener(topic = "${rocketmq.topic}",consumerGroup = "${rocketmq.group}")
@Component
public class Consumer implements RocketMQListener<String>{

    @Override
    public void onMessage(String s) {
        System.out.println("接收到消息"+s);
    }
}

