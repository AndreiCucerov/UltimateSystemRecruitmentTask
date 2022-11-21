package com.example.demo.Controllers;

import com.example.demo.Dto.StudentDto;
import com.example.demo.Entity.Student;
import com.example.demo.Request.Assign;
import com.example.demo.Services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final ModelMapper modelMapper;
    private final StudentService studentService;
    @GetMapping("/findAll")
    ResponseEntity<List<StudentDto>>findAllStudents(@RequestParam(required = false) String sortType,@RequestParam(required = false)Long teacherId){
        List<StudentDto> studentDtos =studentService.findAll()
                .stream().map(x->modelMapper.map(x,StudentDto.class)).collect(Collectors.toList());
        if(sortType!=null) {
            switch (sortType) {
                case "ByAge":
                    studentDtos = studentDtos.stream().sorted(new Comparator<StudentDto>() {
                        @Override
                        public int compare(StudentDto o1, StudentDto o2) {
                            return o1.getAge() - o2.getAge();
                        }
                    }).collect(Collectors.toList());
                    break;
                    //http://localhost:8080/students/findAll?sortType=ByAge
                case "BySecondName":
                    studentDtos = studentDtos.stream().sorted(new Comparator<StudentDto>() {
                        @Override
                        public int compare(StudentDto o1, StudentDto o2) {
                            return o1.getSecondName().compareTo(o2.getSecondName());
                        }
                    }).collect(Collectors.toList());
                    break;
                    //http://localhost:8080/students/findAll?sortType=BySecondName
                case "ByTeacher":
                    studentDtos=studentService.findStudentsOfTeacher(teacherId)
                            .stream().map(x->modelMapper.map(x,StudentDto.class)).collect(Collectors.toList());
                    break;
                //http://localhost:8080/students/findAll?sortType=ByTeacher&teacherId=3
            }
        }
        return ResponseEntity.ok(studentDtos);
    }

    @GetMapping("/findByFullName")
    ResponseEntity<StudentDto>findStudentByFullName(@RequestParam String firstName, @RequestParam String secondName){
        Student student=studentService.findStudentByFullName(firstName,secondName).get();
        StudentDto studentDto= modelMapper.map(student,StudentDto.class);
        return ResponseEntity.ok(studentDto);
    }
    //http://localhost:8080/students/findByFullName?firstName=Rafal&secondName=Witkowski
    @PostMapping(value = "/assignToTeacher")
    ResponseEntity<String> assignStudentToTeacher(@RequestBody Assign request){
        String result = studentService.assignStudentToTeacher(request.getStudentId(), request.getTeacherId());
        if(result.length()>35){
            return  ResponseEntity.ok(result);
        }else return ResponseEntity.badRequest().body(result);
    }
    //PostMan PostRequest URL=http://localhost:8080/students/assignToTeacher
    //body {
    //    "studentId":1,
    //    "teacherId":5
    //}
}
