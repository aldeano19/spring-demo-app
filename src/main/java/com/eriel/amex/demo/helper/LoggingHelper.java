package com.eriel.amex.demo.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LoggingHelper {
    private static LoggingHelper INSTANCE;

    public static final String filePath = "/tmp/demo-spring-app.log";
    private static FileWriter fileWriter;

    private LoggingHelper() {

        try {
            fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LoggingHelper getInstance() {
        if(INSTANCE == null){
            INSTANCE = new LoggingHelper();
        }

        return INSTANCE;
    }

    public void logLine(String message) {
        try {
            fileWriter.write(formatMessage(message)+"\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        try {
            fileWriter.write(formatMessage(message));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatMessage(String message){
        Date date = new Date();
        String datePattern = "yyyy-MM-dd HH:mm:ss.SSS";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern, Locale.getDefault());
        String stringDate = simpleDateFormat.format(date);

        return String.format("%-30s --- [%20s] %s", stringDate, Thread.currentThread().getName(), message);
    }
}
