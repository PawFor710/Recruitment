package com.exercise.recruitment.repository;

import com.exercise.recruitment.domain.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findAll(Pageable pageable);
    List<Student> findAllByName(String studentName);
    Optional<Student> findById(Long studentId);
    List<Student> findAllBySurname(String studentSurname);
    List<Student> findAllByNameAndSurname(String studentName,String studentSurname);




}
