package com.demo.mongo_integration.controller;

import com.demo.mongo_integration.entity.Student;
import com.demo.mongo_integration.service.WFStudentService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all students (WebFlux)", description = "Retrieve all students from the database using WebFlux")
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllStudents() {
        return studentService.getAllStudents()
                .delayElements(Duration.ofSeconds(5))
                .doOnNext(student ->
                        System.out.println("Emitted: " + student + " at " + LocalTime.now()));
    }

    @Operation(summary = "Save a student (WebFlux)", description = "Save a student to the database using WebFlux")
    @PostMapping
    public Mono<Student> saveStudents(@RequestBody Student student){
        return studentService.saveStudents(student);
    }


}
