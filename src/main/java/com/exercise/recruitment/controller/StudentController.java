package com.exercise.recruitment.controller;

import com.exercise.recruitment.domain.StudentDto;
import com.exercise.recruitment.mapper.StudentMapper;
import com.exercise.recruitment.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    
    private final DbService service;
    private final StudentMapper studentMapper;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents(Pageable pageable) {
        return ResponseEntity.ok(studentMapper.mapToStudentDtoList(service.getAllStudents(pageable)));
    }

    @GetMapping(value = "{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long studentId) throws StudentNotFoundException {
        return ResponseEntity.ok(studentMapper.mapToStudentDto(service.getStudentById(studentId)));
    }

    @GetMapping(value = "name/{studentsName}")
    public ResponseEntity<List<StudentDto>> getStudentByName(@PathVariable String studentsName) {
        return ResponseEntity.ok(studentMapper.mapToStudentDtoList(service.getStudentByName(studentsName)));
    }

    @DeleteMapping(value = "{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) throws StudentNotFoundException {
        service.deleteStudentById(studentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentMapper.mapToStudentDto(service
                .saveStudent(studentMapper.mapToStudent(studentDto))));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createStudent(@Valid @RequestBody StudentDto studentDto) {
        service.saveStudent(studentMapper.mapToStudent(studentDto));
        return ResponseEntity.ok().build();
    }
}
