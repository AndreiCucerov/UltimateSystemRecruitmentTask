package com.example.demo.Services;

import com.example.demo.Entity.Student;
import com.example.demo.Entity.Teacher;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImp  implements TeacherService{
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
    @Override
    public Optional<Teacher> findTeacherByFullName(String firstName, String secondName) {
        return teacherRepository.findTeacherByFullName(firstName,secondName);
    }
    @Override
    public List<Teacher> findTeachersOfStudent(Long studentId) {
        return teacherRepository.findTeachersOfStudent(studentId);
    }
    @Override
    public String assignTeacherToStudent(Long studentId, Long teacherId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isEmpty()){
            return "There is no student with such id";
        }
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if(optionalTeacher.isEmpty()){
            return "There is no teacher with such id";
        }
        Student student = optionalStudent.get();
        Teacher teacher = optionalTeacher.get();
        student.getTeachers().add(teacher);
        studentRepository.save(student);
        return "Teacher with id " +teacher.getId()+" assigned to student with id "+student.getId();
    }
}
