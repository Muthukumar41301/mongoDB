package com.demo.mongo_integration;

import lombok.Data;

@Data
public class CgpaStats {
    private Double minCgpa;
    private Double maxCgpa;
    private Double avgCgpa;
    private Double totalCgpa;
    private Double studentCount;
    private String firstName;
    private String lastName;
}
