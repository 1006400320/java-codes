package com.linhuanjie.demos.mq.rocketmq;

import com.linhuanjie.DemoApplication;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
//@Slf4j
public class ProducerTest {

    @Value("${rocketmq.topic}")
    private String topic;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void testSendMessage(){
        System.out.println("topic = " + topic);
        rocketMQTemplate.convertAndSend(topic,"Hello SpringBoot RocketMQ222");
//        log.info("消息发送成功");
        System.out.println("消息发送成功");
    }

}
