package com.demo.mongo_integration.service;

import com.demo.mongo_integration.entity.UploadFiles;
import com.demo.mongo_integration.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FilesRepository filesRepository;

    @KafkaListener(topics = "file", groupId = "group_id")
    public void consumeFile(byte[] fileBytes) {
        try {
            UploadFiles file = new UploadFiles();
            file.setId(2L);
            file.setFileName("testing");
            file.setFile(fileBytes);
            filesRepository.save(file);
            System.out.println("File consumed and saved successfully.");
        } catch (Exception e) {
            System.err.println("Error saving file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
