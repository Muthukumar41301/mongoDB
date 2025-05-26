package com.demo.mongo_integration.repository;

import com.demo.mongo_integration.entity.UploadFiles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends MongoRepository<UploadFiles,Long> {
}
