package com.demo.mongo_integration.controller;

import com.demo.mongo_integration.model.CgpaStats;
import com.demo.mongo_integration.entity.Student;
import com.demo.mongo_integration.service.StudentService;
import io.swagger.v3.oas.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Add a student", description = "Add a single student record to the database")
    @PostMapping("/addStudent")
    public Student populateStudentData(@RequestBody Student student) {
        return studentService.addStudentData(student);
    }

    @Operation(summary = "Add multiple students", description = "Add multiple student records to the database in a single call")
    @PostMapping("/addStudentsData")
    public void populateStudentsData(@RequestBody List<Student> students) {
        studentService.addMultipleStudentsData(students);
    }

    @Operation(summary = "Get all students", description = "Retrieve all student records from the database")
    @GetMapping("/getAllStudentsData")
    public List<Student> fetchAllStudentsData() {
        return studentService.fetchAllStudentsData();
    }

    @Operation(summary = "Get student by ID", description = "Retrieve a student record by its MongoDB ID")
    @GetMapping("/getStudentById/{id}")
    public Optional<Student> fetchStudentDataById(
            @Parameter(description = "MongoDB ID of the student") @PathVariable String id) {
        return studentService.fetchStudentDataById(id);
    }

    @Operation(summary = "Get students by name and CGPA", description = "Retrieve students matching both the given name and CGPA")
    @GetMapping("/getStudentByNameAndCgpa")
    public List<Student> fetchStudentDataByNameAndCgpa(
            @Parameter(description = "Student name to filter by") @RequestParam String name,
            @Parameter(description = "Student CGPA to filter by") @RequestParam Double cgpa) {
        return studentService.fetchStudentDataByNameAndCgpa(name, cgpa);
    }

    @Operation(summary = "Get student by city", description = "Retrieve a single student record by city name")
    @GetMapping("/getStudentByCity/{city}")
    public List<Student> fetchStudentDataByCity(
            @Parameter(description = "City name to filter by") @PathVariable String city) {
        return studentService.fetchStudentDataByCity(city);
    }

    @Operation(summary = "Get students by country or arrears", description = "Retrieve students by matching country or arrears status")
    @GetMapping("/getStudentByCountryOrArrears")
    public List<Student> fetchStudentDataByCountryOrArrears(
            @Parameter(description = "Country to filter by") @RequestParam String country,
            @Parameter(description = "Whether the student has arrears") @RequestParam Boolean hasArrears) {
        return studentService.fetchStudentDataByCountryOrArrears(country, hasArrears);
    }

    @GetMapping("/getStudentByEnrollmentDate")
    @Operation(
            summary = "Get students by enrollment date range",
            description = "Retrieve students enrolled between the specified start and end dates (format: dd-MM-yyyy)"
    )
    public List<Student> fetchStudentDataByEnrollmentDate(
            @Parameter(description = "Start date of enrollment (dd-MM-yyyy)")
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,

            @Parameter(description = "End date of enrollment (dd-MM-yyyy)")
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate) {
        return studentService.fetchStudentDataByEnrollmentDate(startDate, endDate);
    }

    @Operation(summary = "Get students by CGPA", description = "Retrieve students whose CGPA matches the given value")
    @GetMapping("/getStudentByCgpa")
    public List<Student> fetchStudentDataByCgpa(
            @Parameter(description = "Student CGPA to filter by") @RequestParam Double cgpa) {
        return studentService.fetchStudentDataByCgpa(cgpa);
    }

    @Operation(summary = "Get average CGPA", description = "Retrieve the average CGPA of all students")
    @GetMapping("/getAvgCgpa")
    public Double fetchStudentAvgCgpa() {
        return studentService.fetchAverageCgpa();
    }

    @Operation(summary = "Get Total CGPA", description = "Retrieve the total CGPA of all students")
    @GetMapping("/getTotalCgpa")
    public Double fetchStudentTotalCgpa() {
        return studentService.fetchTotalCgpa();
    }

    @Operation(summary = "Get CGPA Stats", description = "Retrieve the maximum and minimum CGPA of all students")
    @GetMapping("/getCgpaStats")
    public CgpaStats getCgpaStats() {
        return studentService.fetchCgpaStats();
    }

    @Operation(summary = "Get students by subject name", description = "Retrieve students who are enrolled in a specific subject")
    @GetMapping("/getStudentBySubject/{subjectName}")
    public List<Student> fetchStudentDataBySubjectName(
            @Parameter(description = "Subject name to filter by") @PathVariable String subjectName) {
        return studentService.fetchStudentDataBySubjectName(subjectName);
    }

    @Operation(summary = "Delete student", description = "Delete a specific student by providing the student object")
    @DeleteMapping("/deleteStudent")
    public void deleteStudentData(
            @Parameter(description = "Student object to be deleted") @RequestBody Student student) {
        studentService.deleteStudentData(student);
    }

    @Operation(summary = "Delete all students", description = "Remove all student records from the database")
    @DeleteMapping("/deleteAllStudents")
    public void deleteAllStudentsData() {
        studentService.deleteAllStudentData();
    }
}
