package com.example.demo.Repository;

import com.example.demo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(value = "Select * From Students Where " +
            "id IN(Select id From Students Join student_teacher " +
            "On Students.id=student_teacher.student_id " +
            "Where student_teacher.teacher_id=?1)",
            nativeQuery = true
    )
    List<Student>findStudentsOfTeacher(Long teacherId);
    @Query(value = "Select * From Students " +
            "Where firstName=?1 AND secondName=?2",
            nativeQuery = true
    )
    Optional<Student>findByFullName(String firstName, String secondName);
}
