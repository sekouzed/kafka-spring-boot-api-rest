package com.sekouzed.kafkaspringbootapirest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "traca";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        try {
            SendResult<String, String> stringStringSendResult = this.kafkaTemplate.send(TOPIC, message).get();
            logger.info(String.format("#### -> Producing message -> %s", message));
        } catch (Exception e) {


            logger.error(String.format("#### -> NotProducing message -> %s", message));

            e.printStackTrace();
        }
    }
}