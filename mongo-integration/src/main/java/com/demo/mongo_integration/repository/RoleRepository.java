package com.demo.mongo_integration.repository;

import com.demo.mongo_integration.entity.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Roles,String> {

}
