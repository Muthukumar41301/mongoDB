package com.demo.mongo_integration.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "roles")
public class Roles {

    @Id
    private String id;

    @Field("role_name")
    @JsonProperty("name")
    private String name;

    @Field("role_description")
    @JsonProperty("description")
    private String description;
}
