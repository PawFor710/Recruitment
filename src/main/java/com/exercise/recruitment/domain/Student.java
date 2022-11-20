package com.exercise.recruitment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@Entity
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
}
