package com.demo.mongo_integration.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Log4j2
public class RedisCacheProvider {

    private final HashOperations<String, String, String> hashOperations;

    @Autowired
    public RedisCacheProvider(RedisTemplate<String, String> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void addData(String hashName, String key, String data) {
        log.info("Adding Data in hashName {} for key {} in Cache ", hashName, key);
        hashOperations.put(hashName, key, data);
    }

    public String getData(String hashName, String key) {
        log.info("Fetching Data using hashName {} for key {} from Cache ", hashName, key);
        return hashOperations.get(hashName, key);
    }

    public Map<String, String> getAllData(String hashName) {
        log.info("Fetching All Data using hashName {} from Cache ", hashName);
        return hashOperations.entries(hashName);
    }

    public void updateData(String hashName, String key, String data) {
        log.info("Updating Data in hashName {} for key {} in Cache ", hashName, key);
        hashOperations.put(hashName, key, data);
    }

    public void deleteData(String hashName, String key) {
        log.info("Deleting Data in hashName {} for key {} in Cache ", hashName, key);
        hashOperations.delete(hashName, key);
    }

    public boolean ifHasKey(String hashName, String key) {
        log.info("Checking if hashName {} available for key {} in Cache ", hashName, key);
        return hashOperations.hasKey(hashName, key);
    }
}
