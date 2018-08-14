package com.eriel.amex.demo.service.impl;

import com.eriel.amex.demo.helper.LoggingHelper;
import com.eriel.amex.demo.service.FileUploadService;
import com.eriel.amex.demo.service.UserService;
import com.eriel.amex.demo.service.impl.runnable.UserZipFileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private LoggingHelper loggingHelper = LoggingHelper.getInstance();

    @Autowired
    private UserService userService;

    @Override
    public void userBulkUpload(MultipartFile multipartFile) {


        Thread zipProcessorThread = new Thread(new UserZipFileProcessor(multipartFile, userService));

        String message = String.format("Starting unzip thread with name = %s", zipProcessorThread.getName());
        loggingHelper.logLine(message);

        zipProcessorThread.start();
    }
}
