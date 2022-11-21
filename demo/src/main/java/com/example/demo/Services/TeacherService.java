package com.example.demo.Services;

import com.example.demo.Entity.Student;
import com.example.demo.Entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> findAll();
    Optional<Teacher> findTeacherByFullName(String firstName, String secondName);
    List<Teacher>findTeachersOfStudent(Long studentId);
    String assignTeacherToStudent(Long studentId, Long teacherId);
}
