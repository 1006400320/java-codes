package com.linhuanjie.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 * @CreateTime : 2020.04.04 19:46
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class ActiveMQTest {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    public void testSendMessage(){
        jmsMessagingTemplate.convertAndSend("queue_name", "consumer_tx");
    }

}
