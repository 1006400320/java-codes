package com.linhuanjie.demos.activemq;

import com.linhuanjie.DemoApplication;
import com.linhuanjie.mq.activemq.MessageService;
import com.linhuanjie.mq.activemq.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;
import java.io.File;
import java.io.FileInputStream;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : SpringBoot 与 ActiveMQ 整合 - 消息生产者
 * @CreateTime : 2020.04.01 22:26
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class SpringBootProducer {

    @Value("${activemq.queue-name}")
    private String name;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MessageService messageService;

    @Test
    public void ptpSender() {
        /**
         * destinationName: 队列名称或主题名称
         * payload: 消息内容
         */
        jmsMessagingTemplate.convertAndSend(name, "springboot_queue_1");
    }

    /**
     * 发送TextMessage消息 （常用）
     */
    @Test
    public void testMessage() {
        jmsTemplate.send(name, session -> {
            TextMessage textMessage = session.createTextMessage("文本消息");
            return textMessage;
        });
    }

    /**
     * 发送MapMessage消息
     */
    @Test
    public void mapMessage() {
        jmsTemplate.send(name, session -> {
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("name", "张三");
            mapMessage.setInt("age", 20);
            return mapMessage;
        });
    }


    /**
     * 发送ObjectMessage消息（常用）
     */
    @Test
    public void objectMessage() {
        jmsTemplate.send(name, session -> {
            User user = new User("小明", "123456");
            ObjectMessage objectMessage = session.createObjectMessage(user);
            return objectMessage;

        });
    }

    /**
     * 发送BytesMessage消息（常用）
     */
    @Test
    public void bytesMessage() {

        jmsTemplate.send(name, session -> {
            BytesMessage bytesMessage = session.createBytesMessage();

            //1.读取文件
            File file = new File("h:/tmp/c0aaa827a2db4df4738986b2832c1188.jpg");

            //2.构建文件输入流
            try {
                FileInputStream inputStream = new FileInputStream(file);

                //3.把文件流写入到缓存数组中
                byte[] buffer = new byte[(int) file.length()];
                inputStream.read(buffer);

                //4.把缓存数组写入到BytesMessage中
                bytesMessage.writeBytes(buffer);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bytesMessage;
        });
    }


    /**
     * 发送StreamMessage消息
     */
    @Test
    public void streamMessage() {

        jmsTemplate.send(name, session -> {
            StreamMessage streamMessage = session.createStreamMessage();
            streamMessage.writeString("你好，ActiveMQ");
            streamMessage.writeInt(20);

            //设置消息属性：标记、过滤
            streamMessage.setStringProperty("订单", "order");

            return streamMessage;
        });

    }

    /**
     * 发送 事务消息 (方式一：原生JMS事务)
     */
    @Test
    public void sendMessageTx() {

        // 创建连接工厂
        ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
        Session session = null;
        try {
            // 创建连接
            Connection connection = connectionFactory.createConnection();
            // 开启事务
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // 不开启事务
//            session = connection.createSession();

            MessageProducer producer = session.createProducer(session.createQueue(name));
            for (int i = 0; i < 10; i++) {
                // 中途报错，没执行 session.commit()， 则前面发送的消息回滚
                if (i == 4) {
                    int a = 1 / 0;
                }
                TextMessage textMessage = session.createTextMessage("send tx message.." + i);
                producer.send(textMessage);
            }

            //注意：一旦开启事务发送，那么就必须使用commit方法进行事务提交，否则消息无法到达MQ服务器
            session.commit();

        } catch (JMSException e) {
            try {
                session.rollback();
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 发送 事务消息 (方式二：Spring的JmsTransactionManager功能 @Transaction)
     */
    @Test
    public void sendMessageTxPlus() {
        messageService.sendMessage();
    }


}
