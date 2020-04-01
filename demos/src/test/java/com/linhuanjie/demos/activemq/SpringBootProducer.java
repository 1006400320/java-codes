package com.linhuanjie.demos.activemq;

import com.linhuanjie.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    private String queueName;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
// private JmsTemplate template;

    @Test
    public void ptpSender(){
        /**
         * destinationName: 队列名称或主题名称
         * payload: 消息内容
         */
        jmsMessagingTemplate.convertAndSend(queueName, "springboot_queue_1");
    }

}
