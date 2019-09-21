package com.sekouzed.kafkaspringbootapirest.controller;

import com.sekouzed.kafkaspringbootapirest.entity.Message;
import com.sekouzed.kafkaspringbootapirest.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer serviceProducer;

    @Autowired
    KafkaController(Producer producer) {
        this.serviceProducer = producer;
    }

    @PostMapping(value = "/publish")
    public ResponseEntity<Message> sendMessageToKafkaTopic(@RequestBody Message message) {
        Message messageSent = this.serviceProducer.send(message);
        return new ResponseEntity<Message>(messageSent, HttpStatus.CREATED);
    }
}