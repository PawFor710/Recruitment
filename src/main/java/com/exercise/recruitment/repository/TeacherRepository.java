package com.exercise.recruitment.repository;

import com.exercise.recruitment.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    List<Teacher> findAll(Pageable pageable);
    List<Teacher> findAllByName(String teacherName);
    Optional<Teacher> findById(Long teacherId);
    List<Teacher> findAllBySurname(String teacherSurname);
}
