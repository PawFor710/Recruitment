package com.exercise.recruitment.controller;

import com.exercise.recruitment.domain.TeacherDto;
import com.exercise.recruitment.mapper.TeacherMapper;
import com.exercise.recruitment.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    
    private final DbService service;
    private final TeacherMapper teacherMapper;

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getTeachers(Pageable pageable) {
        return ResponseEntity.ok(teacherMapper.mapToTeacherDtoList(service.getAllTeachers(pageable)));
    }

    @GetMapping(value = "{teacherId}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long teacherId) throws TeacherNotFoundException {
        return ResponseEntity.ok(teacherMapper.mapToTeacherDto(service.getTeacherById(teacherId)));
    }

    @GetMapping(value = "name/{teachersName}")
    public ResponseEntity<List<TeacherDto>> getTeacherByName(@PathVariable String teachersName) {
        return ResponseEntity.ok(teacherMapper.mapToTeacherDtoList(service.getTeacherByName(teachersName)));
    }

    @DeleteMapping(value = "{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) {
        service.deleteTeacherById(teacherId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto teacherDto) {
        return ResponseEntity.ok(teacherMapper.mapToTeacherDto(service
                .saveTeacher(teacherMapper.mapToTeacher(teacherDto))));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTeacher(@Valid @RequestBody TeacherDto teacherDto) throws MethodArgumentNotValidException {
        service.saveTeacher(teacherMapper.mapToTeacher(teacherDto));
        return ResponseEntity.ok().build();
    }
}
