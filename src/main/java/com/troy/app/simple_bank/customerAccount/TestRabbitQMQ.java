package com.troy.app.simple_bank.customerAccount;

import amqp.RabbitMQProducer;
//import org.junit.Test;

public class TestRabbitQMQ {
    private final RabbitMQProducer rabbitMQProducer;

    public TestRabbitQMQ(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    //@Test
    public void testSendMessage() {
        rabbitMQProducer.sendMessage("connected to RabbitMQ!");

    }

// Sending a message
}
