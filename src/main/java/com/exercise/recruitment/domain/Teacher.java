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
@Entity(name = "teachers")
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
}
