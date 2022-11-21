package com.exercise.recruitment.controller;

import com.exercise.recruitment.domain.Student;
import com.exercise.recruitment.domain.StudentDto;
import com.exercise.recruitment.domain.TeacherDto;
import com.exercise.recruitment.mapper.StudentMapper;
import com.exercise.recruitment.mapper.TeacherMapper;
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
    private final TeacherMapper teacherMapper;

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

    @GetMapping(value = "surname/{studentsSurname}")
    public ResponseEntity<List<StudentDto>> getStudentBySurname(@PathVariable String studentsSurname) {
        return ResponseEntity.ok(studentMapper.mapToStudentDtoList(service.getStudentBySurname(studentsSurname)));
    }

    @GetMapping(value = "fullname/{studentsName}/{studentsSurname}")
    public ResponseEntity<List<StudentDto>> getStudentBySurname(@PathVariable String studentsName,
                                                                @PathVariable String studentsSurname) {
        return ResponseEntity.ok(studentMapper.mapToStudentDtoList(service
                .getStudentByNameAndSurname(studentsName, studentsSurname)));
    }

    @DeleteMapping(value = "{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
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

    @PostMapping(value = "{studentId}/teachers/{teacherId}/add")
    public ResponseEntity<StudentDto> addTeacherToStudent(@PathVariable final Long studentId,
                                                          @PathVariable final Long teacherId)
            throws StudentNotFoundException, TeacherNotFoundException, StudentMembershipException {

        return ResponseEntity.ok(studentMapper.mapToStudentDto(service
                .addTeacherToStudent(studentId, teacherId)));
    }

    @DeleteMapping(value = "{studentId}/teachers/{teacherId}/remove")
    public ResponseEntity<StudentDto> removeTeacherFromStudent(@PathVariable final Long studentId,
                                                          @PathVariable final Long teacherId)
            throws StudentNotFoundException, TeacherNotFoundException {

        return ResponseEntity.ok(studentMapper.mapToStudentDto(service
                .removeTeacherFromStudent(studentId, teacherId)));
    }

    @GetMapping(value = "{studentId}/teachers")
    public ResponseEntity<List<TeacherDto>> getTeachersOfStudents(@PathVariable final Long studentId)
            throws StudentNotFoundException {
        return ResponseEntity.ok(teacherMapper.mapToTeacherDtoList(service.getTeachersOfStudent(studentId)));
    }
}
