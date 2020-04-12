package com.linhuanjie.demos.mq.rocketmq;

import com.linhuanjie.DemoApplication;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
//@Slf4j
public class ProducerTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void testSendMessage(){
        rocketMQTemplate.convertAndSend("SpringBoot-RocketMQ","Hello SpringBoot RocketMQ");
//        log.info("消息发送成功");
        System.out.println("消息发送成功");
    }

}
