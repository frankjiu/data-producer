package com.rabbitmq.producer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author: QiuQiang
 * @Date: 2020-12-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private String id;
    private String name;
    private Integer age;
    private String cardNo;

}
