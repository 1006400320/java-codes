package com.linhuanjie.mq.rabbitmq.simple;

import com.linhuanjie.mq.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author : linhuanjie
 * @createtime : 2020/5/9 16:54
 **/
public class Producer {

    /***
     * 消息生产者
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建链接
        Connection connection = ConnectionUtil.getConnection();

        //创建频道
        Channel channel = connection.createChannel();

        /**
         * 声明队列
         * 参数1： 队列名称
         * 参数2：是否定义持久化队列
         * 参数3：是否独占本次连接
         * 参数4：是否在不使用的时候自动删除队列
         * 参数5：队列其它参数
         * **/
        channel.queueDeclare("simple_queue", true, false, false, null);

        //创建消息
        String message = "hello! RabbitMQ!";

        /**
         * 消息发送
         * 参数1：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数2：路由key,简单模式可以传递队列名称
         * 参数3：消息其它属性
         * 参数4：消息内容
         */
        channel.basicPublish("", "simple_queue", null, message.getBytes());

        //关闭资源
        channel.close();
        connection.close();
    }
}
