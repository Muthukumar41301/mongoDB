package com.demo.mongo_integration.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "address")
public class Address {
    private String street;
    private String city;
    private String state;
    private String country;
    @Field("zip_code")
    private String zipcode;
}
