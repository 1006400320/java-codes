package com.linhuanjie.mq.rabbitmq.springboot;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author : linhuanjie
 * @email : lhuanjie@qq.com
 * @create : 2020.05.10 15:53
 **/
@Component
public class MessageListener {

    /**
     * 监听某个队列的消息
     * @param message 接收到的消息
     */
    @RabbitListener(queues = "item_queue")
    public void myListener1(String message){
        System.out.println("消费者接收到的消息为：" + message);
    }
}
