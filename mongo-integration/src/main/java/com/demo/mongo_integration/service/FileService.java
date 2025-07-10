package com.demo.mongo_integration.service;

import com.demo.mongo_integration.entity.UploadFiles;
import com.demo.mongo_integration.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    @Autowired
    private FilesRepository filesRepository;

    @Autowired
    private S3Service s3Service;

    public UploadFiles uploadFile(MultipartFile file) throws IOException {
        String s3Key = s3Service.uploadFile(file);
        UploadFiles uploadFiles = new UploadFiles();
        uploadFiles.setFileName(file.getOriginalFilename());
        uploadFiles.setS3Key(s3Key);
        return filesRepository.save(uploadFiles);
    }

}
