package com.example.demo.Services;

import com.example.demo.Entity.Student;
import com.example.demo.Entity.Teacher;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    Optional<Student>findStudentByFullName(String firstName, String secondName);
    List<Student>findStudentsOfTeacher(Long teacherId);
    String assignStudentToTeacher(Long studentId, Long teacherId);
}
