package com.demo.mongo_integration.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "file_details")
public class UploadFiles {

    @Id
    private Long id;

    private String fileName;

    private byte[] file;
}
