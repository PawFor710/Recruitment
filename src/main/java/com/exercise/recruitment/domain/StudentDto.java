package com.exercise.recruitment.domain;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long id;
    @NotEmpty(message = "Student's name can not be empty")
    @Size(min = 3, message = "Student's name should be longer then two letter")
    private String name;
    private String surname;
    @Min(value = 18, message = "Student's age should be bigger then 18")
    private int age;
    @Email
    private String email;
    private String major;

}
