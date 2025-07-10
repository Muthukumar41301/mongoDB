package com.demo.mongo_integration.repository;

import com.demo.mongo_integration.entity.Subjects;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends MongoRepository<Subjects, String> {
}
