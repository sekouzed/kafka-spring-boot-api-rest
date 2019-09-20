package com.sekouzed.kafkaspringbootapirest.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class Producer {
    private static final String TOPIC = "traca";
    private Properties props;
    private KafkaProducer<String, String> producer;

    public Producer() {
        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", "0");
        props.put("delivery.timeout.ms", "12000");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public void sendMessage(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC,message);
        producer.send(record, new ProducerCallback(message));
        //producer.close();
    }
}