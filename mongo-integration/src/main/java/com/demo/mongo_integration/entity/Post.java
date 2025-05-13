package com.demo.mongo_integration.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "posts")
public class Post {

    @Id
    private String id;
    private String title;
}
