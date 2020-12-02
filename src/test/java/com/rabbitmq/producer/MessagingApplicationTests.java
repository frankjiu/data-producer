package com.rabbitmq.producer;

import com.rabbitmq.producer.controller.MessageProducer;
import com.rabbitmq.producer.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagingApplicationTests {

    @Resource
    MessageProducer messageProducer;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSend() {
        messageProducer.send(new Person("111", "frank", 25, "4290061988"));
    }

}
