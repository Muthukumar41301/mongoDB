package com.demo.mongo_integration.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class Users {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String email;

    @DBRef
    private Roles role; //One-to-One

    @DBRef
    private List<Post> posts; //One-to-Many
}
