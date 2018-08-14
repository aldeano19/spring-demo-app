package com.eriel.amex.demo.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * DO NOT ATTEMPT TO UNDERSTAND THIS CLASS, ITS BAD DESIGN ON PURPOSE.
 *
 * The purpose of this class is to demonstrate method synchronization rather than an actual logger, because of that a
 * series of unnecessary methods where implemented so that the log file would be jumbled if the log methods are not
 * synchronized.
 */
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

    synchronized public void logLine(String message) {
        message = formatMessage(message);
        logFirstThird(message);
        logMiddleThird(message);
        logLastThird(message);

        try {
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public void log(String message) {
        message = formatMessage(message);
        logFirstThird(message);
        logMiddleThird(message);
        logLastThird(message);
    }

    private String formatMessage(String message){
        Date date = new Date();
        String datePattern = "yyyy-MM-dd HH:mm:ss.SSS";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern, Locale.getDefault());
        String stringDate = simpleDateFormat.format(date);

        return String.format("%-30s --- [%20s] %s", stringDate, Thread.currentThread().getName(), message);
    }

    private void logFirstThird(String str){
        try {
            fileWriter.write(str.substring(0,str.length()/3));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logMiddleThird(String str){
        try {
            fileWriter.write(str.substring(str.length()/3, (str.length()/3)*2 ));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logLastThird(String str){
        try {
            fileWriter.write(str.substring((str.length()/3)*2));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
