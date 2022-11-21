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
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findStudentByFullName(String firstName, String secondName) {
        return studentRepository.findByFullName(firstName,secondName);
    }


    @Override
    public List<Student> findStudentsOfTeacher(Long teacherId) {
        return studentRepository.findStudentsOfTeacher(teacherId);
    }

    @Override
    public String assignStudentToTeacher(Long studentId, Long teacherId) {
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
        return "Student with id " +student.getId()+" assigned to teacher with id "+teacher.getId();
    }

}
