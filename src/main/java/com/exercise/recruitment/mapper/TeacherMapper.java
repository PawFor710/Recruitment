package com.exercise.recruitment.mapper;

import com.exercise.recruitment.domain.Teacher;
import com.exercise.recruitment.domain.TeacherDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherMapper {

    public Teacher mapToTeacher(final TeacherDto teacherDto) {
        return new Teacher(
                teacherDto.getId(),
                teacherDto.getName(),
                teacherDto.getSurname(),
                teacherDto.getAge(),
                teacherDto.getEmail(),
                teacherDto.getSubject());
    }

    public TeacherDto mapToTeacherDto(final Teacher teacher) {
        return  new TeacherDto(
                teacher.getId(),
                teacher.getName(),
                teacher.getSurname(),
                teacher.getAge(),
                teacher.getEmail(),
                teacher.getSubject());
    }

    public List<TeacherDto> mapToTeacherDtoList(final List<Teacher> teacherList) {
        return teacherList.stream()
                .map(this::mapToTeacherDto)
                .collect(Collectors.toList());
    }
}
