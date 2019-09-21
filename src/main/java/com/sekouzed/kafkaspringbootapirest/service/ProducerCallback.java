package com.sekouzed.kafkaspringbootapirest.service;

import com.sekouzed.kafkaspringbootapirest.entity.Message;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ProducerCallback implements Callback {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private String traceFile="trace_file.log";
    private Message message;

    public ProducerCallback(Message message) {
        this.message = message;
    }

    @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e == null){
                logger.info(String.format("Producer send the message: %s", message));
            }
            else{
                logger.error(String.format("Producer failed to send the message: %s", message));
                e.printStackTrace();

                PrintWriter printWriter = null;

                try {

                    printWriter = new PrintWriter(new FileWriter(traceFile, true));
                    printWriter.printf("%s\r\n", message.toString());
                    logger.info(String.format("Written the message: %s in to file: %s", message, traceFile));

                } catch (IOException e1) {

                    logger.error(String.format("Fail to write the message: %s in to file: %s", message, traceFile));
                    e1.printStackTrace();

                } finally {

                    printWriter.close();

                }
            }
        }
}