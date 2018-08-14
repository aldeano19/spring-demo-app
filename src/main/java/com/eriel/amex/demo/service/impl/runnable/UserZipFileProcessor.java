package com.eriel.amex.demo.service.impl.runnable;

import com.eriel.amex.demo.helper.LoggingHelper;
import com.eriel.amex.demo.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UserZipFileProcessor implements Runnable {

    private LoggingHelper loggingHelper = LoggingHelper.getInstance();

    private MultipartFile multipartFile;

    private UserService userService;

    public UserZipFileProcessor(MultipartFile multipartFile, UserService userService){
        this.multipartFile = multipartFile;
        this.userService = userService;
    }

    @Override
    public void run() {
        try {
            List<File> files = unzipFile(multipartFile.getInputStream());

            for(File file : files){
                Thread xlsxFileProcessorThread = new Thread(new XLXSFileProcessor(file, userService));

                String message = String.format("Starting row parsing thread with name = %s", xlsxFileProcessorThread.getName());
                loggingHelper.logLine(message);

                xlsxFileProcessorThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
            loggingHelper.logLine(e.getMessage());
        }
    }

    private List<File> unzipFile(InputStream fileInputStream) throws IOException {
        List<File> outFiles = new ArrayList<>();

        byte[] buffer = new byte[1024];
        ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);


        ZipEntry zipEntry = zipInputStream.getNextEntry();
        while (zipEntry != null){
            String filename = zipEntry.getName();
            File newFile = new File("/tmp/"+filename);
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            int len;
            while ((len = zipInputStream.read(buffer)) > 0){
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.close();
            zipEntry = zipInputStream.getNextEntry();

            outFiles.add(newFile);
        }
        zipInputStream.closeEntry();
        zipInputStream.close();

        return outFiles;
    }
}
