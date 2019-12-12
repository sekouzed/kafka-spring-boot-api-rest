package com.sekouzed.kafkaspringbootapirest.controller;

import com.sekouzed.kafkaspringbootapirest.entity.Message;
import com.sekouzed.kafkaspringbootapirest.service.ProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "messages")
@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    private ProducerService producerService;

    @ApiOperation(value = "publish a message in to kafka")
    @PostMapping(value = "/publish")
    public ResponseEntity<Message> publish(@RequestBody Message message) {
        Message sendMessage = this.producerService.send(message);
        return new ResponseEntity<Message>(sendMessage, HttpStatus.CREATED);
    }
}