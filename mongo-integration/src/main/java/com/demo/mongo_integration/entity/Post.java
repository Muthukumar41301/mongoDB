package com.demo.mongo_integration.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "posts")
public class Post {

    @Id
    private String id;
    private String title;
}
