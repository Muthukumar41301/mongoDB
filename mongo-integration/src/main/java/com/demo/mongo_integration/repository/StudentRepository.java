package com.demo.mongo_integration.repository;

import com.demo.mongo_integration.model.CgpaStats;
import com.demo.mongo_integration.entity.Student;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
    List<Student> findByNameAndCgpa(String name, Double cgpa);

    List<Student> findByAddress_City(String city);

    List<Student> findByAddress_CountryOrHasArrears(String country, Boolean hasArrears);

    List<Student> findByEnrollmentDateBetweenOrderByEnrollmentDate(LocalDate startDate, LocalDate endDate);

    String findByNameIgnoreCase(String name);

    List<Student> findByCgpaGreaterThanEqual(Double cgpa);

    @Aggregation("{ $group : { _id : null, averageCgpa : { $avg : $cgpa} } }")
    Long avgCgpa();

    @Aggregation("{ $group : { _id : null , totalCgpa : { $sum : $cgpa} } }")
    Long totalCgpa();

    @Aggregation("{ $group : { _id : null , minCgpa : { $min : $cgpa}, maxCgpa : { $max : $cgpa}, avgCgpa : { $avg : $cgpa}, totalCgpa : { $sum : $cgpa } ," +
            "studentCount : { $sum : 1 }, firstName : { $first : $name }, lastName : { $last : $name } } }")
    CgpaStats fetchCgpaStats();
}
