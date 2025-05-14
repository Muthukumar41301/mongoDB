package com.demo.mongo_integration.service;

import com.demo.mongo_integration.entity.Student;
import com.demo.mongo_integration.repository.WFStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class WFStudentService {

    @Autowired
    private WFStudentRepository wfStudentRepository;

    public Flux<Student> getAllStudents() {
        return wfStudentRepository.findAll();
    }
}
