package com.demo.mongo_integration.service;

import com.demo.mongo_integration.CgpaStats;
import com.demo.mongo_integration.entity.Student;
import com.demo.mongo_integration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @CachePut(value = "student", key = "#user.id")
    public void addStudentData(Student student) {
        studentRepository.save(student);
    }

    public void addMultipleStudentsData(List<Student> students) {
        studentRepository.insert(students);
    }

    public List<Student> fetchAllStudentsData() {
        return studentRepository.findAll();
    }

    @Cacheable(value = "student", key = "#id")
    public Optional<Student> fetchStudentDataById(String id) {
        return studentRepository.findById(id);
    }

    public List<Student> fetchStudentDataByNameAndCgpa(String name, Double cgpa) {
        return studentRepository.findByNameAndCgpa(name,cgpa);
    }

    public List<Student> fetchStudentDataByCity(String city) {
        return studentRepository.findByAddress_City(city);
    }

    public List<Student> fetchStudentDataByCountryOrArrears(String country, Boolean hasArrears) {
        return studentRepository.findByAddress_CountryOrHasArrears(country, hasArrears);
    }

    public List<Student> fetchStudentDataByEnrollmentDate(LocalDate startDate, LocalDate endDate) {
        return studentRepository.findByEnrollmentDateBetweenOrderByEnrollmentDate(startDate, endDate);
    }

    public String fetchStudentDataByName(String name) {
        return studentRepository.findByNameIgnoreCase(name);
    }

    public List<Student> fetchStudentDataByCgpa(Double cgpa) {
        return studentRepository.findByCgpaGreaterThanEqual(cgpa);
    }

    public Long fetchAverageCgpa() {
        return studentRepository.avgCgpa();
    }

    public void deleteStudentData(Student student) {
        studentRepository.insert(student);
    }

    public void deleteAllStudentData() {
        studentRepository.deleteAll();
    }

    public Long fetchTotalCgpa() {
        return studentRepository.totalCgpa();
    }

    public CgpaStats fetchCgpaStats() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group()
                        .min("cgpa").as("minCgpa")
                        .max("cgpa").as("maxCgpa")
                        .avg("cgpa").as("avgCgpa")
                        .sum("cgpa").as("totalCgpa")
                        .count().as("studentCount")
                        .first("name").as("firstName")
                        .last("name").as("lastName")
        );

        AggregationResults<CgpaStats> results = mongoTemplate.aggregate(aggregation, "student", CgpaStats.class);
        return results.getUniqueMappedResult();
    }
}
