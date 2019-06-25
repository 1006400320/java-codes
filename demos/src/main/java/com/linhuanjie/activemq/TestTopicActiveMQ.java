package com.linhuanjie.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: linhuanjie
 * @description: 一对多（topic）
 * tips: 消费者先订阅，发布者再发布
 * @createTime : 2019-05-19 13:17
 * @email: lhuanjie@qq.com
 */
public class TestTopicActiveMQ implements Runnable {

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for(int i=0;i<5;i++) {
                executorService.execute(new TestTopicActiveMQ());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ActiveMQ 发布者
     *
     * @throws Exception
     */
    public void testMQProducerTopic() throws Exception {
        //1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Topic topic = session.createTopic("test_topic");
        //6、使用会话对象创建生产者对象
        MessageProducer producer = session.createProducer(topic);
        //7、使用会话对象创建一个消息对象
        TextMessage textMessage = session.createTextMessage("hello!test-topic");
        //8、发送消息
        producer.send(textMessage);
        System.out.println("send_topic:" + textMessage.getText());
        //9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    /**
     * ActiveMQ 消费者
     *
     * @throws Exception
     */
    public void testMQConsumerTopic() throws Exception {
        //1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Topic topic = session.createTopic("test_topic");
        //6、使用会话对象创建生产者对象
        MessageConsumer consumer = session.createConsumer(topic);
        //7、向consumer对象中设置一个messageListener对象，用来接收消息
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("receive_topic:" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //8、程序等待接收用户消息
        System.out.println("开始订阅...");
        System.in.read();
        //9、关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            int num = random.nextInt(10);
            if (num % 2 == 1) {
                this.testMQProducerTopic();
            } else {
                this.testMQConsumerTopic();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
