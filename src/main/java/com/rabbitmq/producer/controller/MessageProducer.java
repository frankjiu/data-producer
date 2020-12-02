package com.rabbitmq.producer.controller;

import com.rabbitmq.producer.entity.Person;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description:
 * @Author: QiuQiang
 * @Date: 2020-12-01
 */
@Component
public class MessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        // correlationData: 消息附加信息, 即自定义id
        // isack: 代表消息是否被broker接收, true表示接收, false表示拒收.
        // cause: 如果拒收则说明拒收原因, 帮助我们进行后续处理
        @Override
        public void confirm(CorrelationData correlationData, boolean isack, String cause) {
            System.out.println(correlationData);
            System.out.println(isack);
            if (isack == false) {
                System.err.println(cause);
            }
        }
    };

    RabbitTemplate.ReturnsCallback returnsCallback = new RabbitTemplate.ReturnsCallback() {
        @Override
        public void returnedMessage(ReturnedMessage returned) {
            System.err.println(returned.getExchange() + "===" + returned.getRoutingKey());
            System.err.println(returned.getReplyCode() + "===" + returned.getReplyText());
        }
    };

    public void send(Person person){
        CorrelationData dataId = new CorrelationData(person.getCardNo() + "-" + new Date().getTime());
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnsCallback(returnsCallback);
        rabbitTemplate.convertAndSend("springboot-exchange","person-info", person, dataId);
    }


}
