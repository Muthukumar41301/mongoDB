package com.demo.mongo_integration.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "subjects")
public class Subjects {

    @Id
    @Indexed(unique = true)
    private String id;

    private String name;
}
