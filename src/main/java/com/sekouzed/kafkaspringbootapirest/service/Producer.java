package com.sekouzed.kafkaspringbootapirest.service;

import com.sekouzed.kafkaspringbootapirest.entity.Message;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final String TOPIC = "traca";

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public Message send(Message message) {
        ProducerRecord<String, Message> record = new ProducerRecord<>(TOPIC, message);

        this.kafkaTemplate.send(record).addCallback(new ProducerCallback(message));

        return message;
    }

}