package com.sekouzed.kafkaspringbootapirest.service;

import com.sekouzed.kafkaspringbootapirest.entity.Message;
import com.sekouzed.kafkaspringbootapirest.utility.FSUtility;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Value(value = "${app.kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public Message send(Message sendMessage) {
        ProducerRecord<String, Message> record = new ProducerRecord<>(topic, sendMessage);

        this.kafkaTemplate.send(record).addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {

            @Override
            public void onSuccess(SendResult<String, Message> sendResult) {
                logger.info(String.format("--> Producer send the message: %s with result: %s", sendMessage,sendResult));
            }

            @Override
            public void onFailure(Throwable throwable) {
                logger.error(String.format("--> Producer failed to send the message: %s with exception: %s", sendMessage,throwable.getMessage()));
                FSUtility.saveInToFile(sendMessage.toString());
            }
        });

        return sendMessage;
    }

}