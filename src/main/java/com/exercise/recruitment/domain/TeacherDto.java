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
public class TeacherDto {

    private Long id;
    @NotEmpty(message = "Teacher's name can not be empty")
    @Size(min = 3, message = "Teacher's name should be longer then two letter")
    private String name;
    private String surname;
    @Min(value = 20, message = "Teacher's age should be bigger then 20")
    private int age;
    @Email
    private String email;
    private String subject;

}
