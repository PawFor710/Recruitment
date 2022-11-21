package com.exercise.recruitment.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "teachers_university")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NotEmpty(message = "Teacher's name can not be empty")
    @Size(min = 3, message = "Teacher's name should be longer then two letter")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    @Min(value = 20, message = "Teacher's age should be bigger then 20")
    private int age;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "subject")
    private String subject;

    @ManyToMany
    @JoinTable(
            name = "teachers_students",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Teacher(Long id, String name, String surname, int age, String email, String subject) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.subject = subject;
    }
}
