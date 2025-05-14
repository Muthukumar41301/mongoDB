package com.demo.mongo_integration.repository;

import com.demo.mongo_integration.entity.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WFStudentRepository extends ReactiveMongoRepository<Student ,String> {
}
