package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private  long id;
    @Size(min = 3)
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String secondName;
    @Email
    private String email;
    @Min(18)
    private int age;
    private String subject;
}
