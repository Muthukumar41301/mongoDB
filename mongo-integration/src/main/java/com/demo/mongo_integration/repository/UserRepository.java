package com.demo.mongo_integration.repository;

import com.demo.mongo_integration.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<Users,String> {

    @Query("{'name': ?0}")
    List<Users> findByUserNames(String userName);
}
