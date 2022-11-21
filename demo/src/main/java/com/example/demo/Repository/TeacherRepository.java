package com.example.demo.Repository;

import com.example.demo.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query(value = "Select * From Teachers Where " +
            "id IN(Select id From Teachers JOIN student_teacher " +
            "On Teachers.id=student_teacher.teacher_id " +
            "Where student_teacher.student_id=?1)",
            nativeQuery = true
    )
    public List<Teacher> findTeachersOfStudent(Long id);
    @Query(value = "Select * From Teachers " +
            "Where firstName=?1 AND secondName=?2",
            nativeQuery = true
    )
    Optional<Teacher>findTeacherByFullName(String firstName , String SecondName);
}
