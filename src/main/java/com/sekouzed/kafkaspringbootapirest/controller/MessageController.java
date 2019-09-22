package com.sekouzed.kafkaspringbootapirest.controller;

import com.sekouzed.kafkaspringbootapirest.entity.Message;
import com.sekouzed.kafkaspringbootapirest.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    private ProducerService producerService;

    @PostMapping(value = "/publish")
    public ResponseEntity<Message> publish(@RequestBody Message message) {
        Message sendMessage = this.producerService.send(message);
        return new ResponseEntity<Message>(sendMessage, HttpStatus.CREATED);
    }
}