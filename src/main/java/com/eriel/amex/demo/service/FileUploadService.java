package com.eriel.amex.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    void userBulkUpload(MultipartFile multipartFile);
}
