package com.demo.mongo_integration.service;

import com.demo.mongo_integration.CgpaStats;
import com.demo.mongo_integration.entity.Student;
import com.demo.mongo_integration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void addStudentData(Student student) {
        studentRepository.save(student);
    }

    public void addMultipleStudentsData(List<Student> students) {
        studentRepository.insert(students);
    }

    public List<Student> fetchAllStudentsData() {
        return studentRepository.findAll();
    }

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
        return studentRepository.fetchCgpaStats();
    }
}
