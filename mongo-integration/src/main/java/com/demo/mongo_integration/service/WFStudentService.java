package com.demo.mongo_integration.service;

import com.demo.mongo_integration.entity.Student;
import com.demo.mongo_integration.repository.WFStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WFStudentService {

    @Autowired
    private WFStudentRepository wfStudentRepository;

    public Flux<Student> getAllStudents() {
        return wfStudentRepository.findAll();
    }

    public Mono<Student> saveStudents(Student student) {
        return wfStudentRepository.save(student);
    }
}
