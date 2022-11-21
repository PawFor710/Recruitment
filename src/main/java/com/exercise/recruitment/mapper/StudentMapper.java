package com.exercise.recruitment.mapper;

import com.exercise.recruitment.domain.Student;
import com.exercise.recruitment.domain.StudentDto;
import com.exercise.recruitment.domain.Teacher;
import com.exercise.recruitment.domain.TeacherDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentMapper {

    public Student mapToStudent(final StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getName(),
                studentDto.getSurname(),
                studentDto.getAge(),
                studentDto.getEmail(),
                studentDto.getMajor());

    }

    public StudentDto mapToStudentDto(final Student student) {
        return  new StudentDto(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getAge(),
                student.getEmail(),
                student.getMajor());
    }

    public List<StudentDto> mapToStudentDtoList(final List<Student> studentList) {
        return studentList.stream()
                .map(this::mapToStudentDto)
                .collect(Collectors.toList());
    }
}
