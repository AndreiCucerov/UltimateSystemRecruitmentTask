package com.example.demo.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(mappedBy = "teachers")
    private List<Student> students;
}
