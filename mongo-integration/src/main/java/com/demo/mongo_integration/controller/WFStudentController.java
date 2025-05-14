package com.demo.mongo_integration.controller;

import com.demo.mongo_integration.entity.Student;
import com.demo.mongo_integration.service.WFStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

// Flux - Represents a reactive sequence of 0 to N items
// Mono -Represents a reactive sequence of 0 or 1 items

@RestController
@RequestMapping("/wf-students")
public class WFStudentController {

    @Autowired
    private WFStudentService studentService;

    @GetMapping
    public Flux<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


}
