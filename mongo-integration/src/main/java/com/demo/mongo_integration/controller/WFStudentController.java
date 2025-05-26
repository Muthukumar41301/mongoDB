package com.demo.mongo_integration.controller;

import com.demo.mongo_integration.entity.Student;
import com.demo.mongo_integration.service.WFStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalTime;

// Flux - Represents a reactive sequence of 0 to N items
// Mono - Represents a reactive sequence of 0 or 1 items

@RestController
@RequestMapping("/wf-students")
public class WFStudentController {

    @Autowired
    private WFStudentService studentService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllStudents() {
        return studentService.getAllStudents()
                .delayElements(Duration.ofSeconds(5))
                .doOnNext(student ->
                        System.out.println("Emitted: " + student + " at " + LocalTime.now()));
    }

    @PostMapping
    public Mono<Student> saveStudents(@RequestBody Student student){
        return studentService.saveStudents(student);
    }


}
