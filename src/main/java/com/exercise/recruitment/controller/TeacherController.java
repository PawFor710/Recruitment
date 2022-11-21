package com.exercise.recruitment.controller;

import com.exercise.recruitment.domain.StudentDto;
import com.exercise.recruitment.domain.TeacherDto;
import com.exercise.recruitment.mapper.StudentMapper;
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
    private final StudentMapper studentMapper;

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

    @GetMapping(value = "surname/{teachersSurname}")
    public ResponseEntity<List<TeacherDto>> getTeacherBySurname(@PathVariable String teachersSurname) {
        return ResponseEntity.ok(teacherMapper.mapToTeacherDtoList(service.getTeacherBySurname(teachersSurname)));
    }

    @GetMapping(value = "fullname/{teachersName}/{teachersSurname}")
    public ResponseEntity<List<TeacherDto>> getStudentBySurname(@PathVariable String teachersName,
                                                                @PathVariable String teachersSurname) {
        return ResponseEntity.ok(teacherMapper.mapToTeacherDtoList(service
                .getTeacherByNameAndSurname(teachersName, teachersSurname)));
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
    public ResponseEntity<Void> createTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        service.saveTeacher(teacherMapper.mapToTeacher(teacherDto));
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "{teacherId}/students/{studentId}/add")
    public ResponseEntity<TeacherDto> addTeacherToStudent(@PathVariable final Long teacherId,
                                                          @PathVariable final Long studentId)
            throws StudentNotFoundException, TeacherNotFoundException, TeacherMembershipException {

        return ResponseEntity.ok(teacherMapper.mapToTeacherDto(service
                .addStudentToTeacher(teacherId, studentId)));
    }

    @DeleteMapping(value = "{teacherId}/students/{studentId}/remove")
    public ResponseEntity<TeacherDto> removeTeacherFromStudent(@PathVariable final Long teacherId,
                                                          @PathVariable final Long studentId)
            throws StudentNotFoundException, TeacherNotFoundException {

        return ResponseEntity.ok(teacherMapper.mapToTeacherDto(service
                .removeStudentFromTeacher(teacherId, studentId)));
    }

    @GetMapping(value = "{teacherId}/students")
    public ResponseEntity<List<StudentDto>> getStudentsOfTeacher(@PathVariable final Long teacherId) throws TeacherNotFoundException {
        return ResponseEntity.ok(studentMapper.mapToStudentDtoList(service.getStudentsOfTeacher(teacherId)));
    }

}
