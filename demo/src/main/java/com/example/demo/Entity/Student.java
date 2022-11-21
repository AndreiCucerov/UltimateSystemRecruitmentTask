package com.example.demo.Entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 3)
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String secondName;
    @Email
    private String email;
    @Min(18)
    private int age;
    private String specialization;
    @ManyToMany
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name ="student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers;
}
