package com.exercise.recruitment.service;

import com.exercise.recruitment.controller.StudentMembershipException;
import com.exercise.recruitment.controller.StudentNotFoundException;
import com.exercise.recruitment.controller.TeacherMembershipException;
import com.exercise.recruitment.controller.TeacherNotFoundException;
import com.exercise.recruitment.domain.Student;
import com.exercise.recruitment.domain.Teacher;
import com.exercise.recruitment.repository.StudentRepository;
import com.exercise.recruitment.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<Student> getStudentBySurname(String studentSurname) {
        return studentRepository.findAllBySurname(studentSurname);
    }

    public List<Teacher> getTeacherBySurname(String teacherSurname) {
        return teacherRepository.findAllBySurname(teacherSurname);
    }

    public List<Student> getStudentByNameAndSurname(String studentName, String studentSurname) {
        return studentRepository.findAllByNameAndSurname(studentName, studentSurname);
    }

    public List<Teacher> getTeacherByNameAndSurname(String teacherName, String teacherSurname) {
        return teacherRepository.findAllByNameAndSurname(teacherName, teacherSurname);
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

    @Transactional
    public Student addTeacherToStudent(Long studentId, Long  teacherId) throws StudentNotFoundException, TeacherNotFoundException, StudentMembershipException {

        Student student = getStudentById(studentId);
        Teacher teacher = getTeacherById(teacherId);
        if (!student.getTeachers().contains(teacher)) {
            teacher.addStudent(student);
            student.addTeacher(teacher);
        } else {
            throw new StudentMembershipException();
        }
        return student;
    }

    @Transactional
    public Teacher addStudentToTeacher(Long teacherId, Long studentId) throws StudentNotFoundException, TeacherNotFoundException, TeacherMembershipException {
        Teacher teacher = getTeacherById(teacherId);
        Student student = getStudentById(studentId);
        if (!teacher.getStudents().contains(student)) {
            student.addTeacher(teacher);
            teacher.addStudent(student);
        } else {
            throw new TeacherMembershipException();
        }
        return teacher;
    }

    @Transactional
    public Student removeTeacherFromStudent(Long studentId, Long  teacherId) throws StudentNotFoundException, TeacherNotFoundException {
        Student student = getStudentById(studentId);
        Teacher teacher = getTeacherById(teacherId);
        teacher.removeStudent(student);
        student.removeTeacher(teacher);
        return student;
    }

    @Transactional
    public Teacher removeStudentFromTeacher(Long teacherId, Long studentId) throws StudentNotFoundException, TeacherNotFoundException {
        Teacher teacher = getTeacherById(teacherId);
        Student student = getStudentById(studentId);
        student.removeTeacher(teacher);
        teacher.removeStudent(student);
        return teacher;
    }

    public List<Teacher> getTeachersOfStudent(Long studentId) throws StudentNotFoundException {
        Student student = getStudentById(studentId);
        return student.getTeachers();
    }

    public List<Student> getStudentsOfTeacher(Long teacherId) throws TeacherNotFoundException {
        Teacher teacher = getTeacherById(teacherId);
        return teacher.getStudents();
    }
}
