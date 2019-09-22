package com.sekouzed.kafkaspringbootapirest.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FSUtility {

    private static final Logger logger = LoggerFactory.getLogger(FSUtility.class);

    private static String FILE = "messages.log";

    public static void saveInToFile(String content) {

        PrintWriter printWriter = null;

        try {

            printWriter = new PrintWriter(new FileWriter(FILE, true));
            printWriter.printf("%s\r\n", content);

            logger.info(String.format("--> Written the message: %s in to file: %s", content, FILE));

        } catch (IOException e1) {

            logger.error(String.format("--> Fail to write the message: %s in to file: %s with exception: %s", content, FILE, e1.getMessage()));

        } finally {

            if (printWriter != null) {
                printWriter.close();
            }

        }
    }
}
