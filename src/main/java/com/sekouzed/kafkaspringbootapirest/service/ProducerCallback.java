package com.sekouzed.kafkaspringbootapirest.service;

import com.sekouzed.kafkaspringbootapirest.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ProducerCallback implements ListenableFutureCallback<SendResult<String, Message>> {

    private static final Logger logger = LoggerFactory.getLogger(ProducerCallback.class);

    private String traceFile="trace_file.log";

    private Message message;

    public ProducerCallback(Message message) {
        this.message = message;
    }

    @Override
    public void onSuccess(SendResult<String, Message> sendResult) {
        logger.info(String.format("--> Producer send the message: %s with result: %s", message,sendResult));
    }

    @Override
    public void onFailure(Throwable throwable) {
        logger.error(String.format("--> Producer failed to send the message: %s with exception: %s", message,throwable.getMessage()));

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(traceFile, true));
            printWriter.printf("%s\r\n", message.toString());
            logger.info(String.format("--> Written the message: %s in to file: %s", message, traceFile));
        } catch (IOException e1) {
            logger.error(String.format("--> Fail to write the message: %s in to file: %s with exception: %s", message, traceFile,e1.getMessage()));
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}