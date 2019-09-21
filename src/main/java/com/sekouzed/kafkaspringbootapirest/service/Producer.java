package com.sekouzed.kafkaspringbootapirest.service;

import com.sekouzed.kafkaspringbootapirest.entity.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class Producer {

    private String bootstrapServers="localhost:9092";
    private String topic="traca";
    private KafkaProducer<String, Message> producer;

    public Producer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        producer = new KafkaProducer<>(props);
    }

    public Message send(Message message) {
        ProducerRecord<String, Message> record = new ProducerRecord<>(topic, message);
        producer.send(record, new ProducerCallback(message));
        return message;
    }

}