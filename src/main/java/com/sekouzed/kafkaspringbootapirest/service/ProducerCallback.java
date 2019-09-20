package com.sekouzed.kafkaspringbootapirest.service;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

class ProducerCallback implements Callback {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private String message = "";

    public ProducerCallback(String message) {
        this.message = message;
    }

    @Override
       public  void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null){
                logger.error(String.format("----------AsynchronousProducer failed with an exception--------"));
                try {
                    fileWrite();
                } catch (Exception e1) {
                    logger.error(String.format("----------AsynchronousProducer failed with an exception--------"));
                    e1.printStackTrace();
                }
            }
            else{
                logger.info(String.format("----------AsynchronousProducer call Success------"));
            }
       }

    public void fileWrite() throws Exception {
        String traca_file = "traca_file.json";
        logger.info(String.format("---------- Writing to file -> %s", traca_file));
        try(PrintWriter output = new PrintWriter(new FileWriter(traca_file,true))){
            output.printf("%s\r\n", message);
        }
//        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(traca_file))) {
//            writer.write(message+"\n");
//        }
//        Files.write(Paths.get("dest.txt"), Collections.singleton("titi"));
    }
}