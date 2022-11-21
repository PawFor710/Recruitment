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
@Entity(name = "students_university")
public class Student {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "name")
    @NotEmpty(message = "Student's name can not be empty")
    @Size(min = 3, message = "Student's name should be longer then two letter")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    @Min(value = 18, message = "Student's age should be bigger then 18")
    private int age;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "major")
    private String major;
    @ManyToMany(mappedBy = "students")
    private List<Teacher> teachers = new ArrayList<>();

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public Student(Long id, String name, String surname, int age, String email, String major) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.major = major;
    }
}
