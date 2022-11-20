package com.exercise.recruitment.service;

import com.exercise.recruitment.controller.StudentNotFoundException;
import com.exercise.recruitment.controller.TeacherNotFoundException;
import com.exercise.recruitment.domain.Student;
import com.exercise.recruitment.domain.Teacher;
import com.exercise.recruitment.repository.StudentRepository;
import com.exercise.recruitment.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public List<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public List<Teacher> getAllTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    public Student getStudentById(Long studentId) throws StudentNotFoundException {
        return studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
    }

    public Teacher getTeacherById(Long teacherId) throws TeacherNotFoundException {
        return teacherRepository.findById(teacherId).orElseThrow(TeacherNotFoundException::new);
    }

    public List<Student> getStudentByName(String studentName) {
        return studentRepository.findAllByName(studentName);
    }

    public List<Teacher> getTeacherByName(String teacherName) {
        return teacherRepository.findAllByName(teacherName);
    }

    public Student saveStudent(final Student student) {
        return studentRepository.save(student);
    }

    public Teacher saveTeacher(final Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void deleteTeacherById(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}
