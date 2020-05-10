package com.linhuanjie.mq.rabbitmq.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*****
 * @Author: shenkunlin
 * @Date: 2019/7/8 3:12
 * @Description:
 ****/
@Configuration
public class RabbitMQConfig {

    /***
     * 声明交换机
     */
    @Bean(name = "itemTopicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange("item_topic_exchange").durable(true).build();
    }

    /***
     * 声明队列
     */
    @Bean(name = "itemQueue")
    public Queue itemQueue(){
        return QueueBuilder.durable("item_queue").build();
    }

    /***
     * 队列绑定到交换机上
     */
    @Bean
    public Binding itemQueueExchange(@Qualifier("itemQueue") Queue queue,
                                     @Qualifier("itemTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
    }
}
