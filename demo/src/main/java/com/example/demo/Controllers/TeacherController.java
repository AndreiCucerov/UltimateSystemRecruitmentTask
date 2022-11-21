package com.example.demo.Controllers;

import com.example.demo.Dto.StudentDto;
import com.example.demo.Dto.TeacherDto;
import com.example.demo.Entity.Teacher;
import com.example.demo.Request.Assign;
import com.example.demo.Services.TeacherService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {
    private final ModelMapper modelMapper;
    private final TeacherService teacherService;
    @GetMapping("/findAll")
    ResponseEntity<List<TeacherDto>>findAllTeachers(@RequestParam (required = false) String sortType, @RequestParam (required = false) Long studentId){
        List<TeacherDto> teacherDtos = teacherService.findAll()
                .stream().map(x->modelMapper.map(x,TeacherDto.class)).collect(Collectors.toList());
        if(sortType!=null){
            switch (sortType){
                case "ByAge":
                    teacherDtos = teacherDtos.stream().sorted(new Comparator<TeacherDto>() {
                        @Override
                        public int compare(TeacherDto o1, TeacherDto o2) {
                            return o1.getAge() - o2.getAge();
                        }
                    }).collect(Collectors.toList());
                    //http://localhost:8080/teachers/findAll?sortType=ByAge
                    break;
                case "BySecondName":
                    teacherDtos = teacherDtos.stream().sorted(new Comparator<TeacherDto>() {
                        @Override
                        public int compare(TeacherDto o1, TeacherDto o2) {
                            return o1.getSecondName().compareTo(o2.getSecondName());
                        }
                    }).collect(Collectors.toList());
                    //http://localhost:8080/teachers/findAll?sortType=BySecondName
                    break;
                case "ByStudent":
                    teacherDtos=teacherService.findTeachersOfStudent(studentId)
                            .stream().map(x->modelMapper.map(x,TeacherDto.class)).collect(Collectors.toList());
                    //http://localhost:8080/teachers/findAll?sortType=ByStudent&studentId=1
                    break;
            }
        }
        return ResponseEntity.ok(teacherDtos);
    }
    @GetMapping("findByFullName")
    ResponseEntity<TeacherDto>findTeacherByFullName(@RequestParam String firstName, @RequestParam String secondName){
        Teacher teacher= teacherService.findTeacherByFullName(firstName,secondName).get();
        TeacherDto teacherDto= modelMapper.map(teacher,TeacherDto.class);
        return  ResponseEntity.ok(teacherDto);
        //http://localhost:8080/teachers/findByFullName?firstName=Olaf&secondName=Sikora
    }
    @PostMapping(value = "/assignToStudent")
    ResponseEntity<String> assignTeacherToStudent(@RequestBody Assign request){
        String result = teacherService.assignTeacherToStudent(request.getStudentId(), request.getTeacherId());
        if(result.length()>35){
            return  ResponseEntity.ok(result);
        }else return ResponseEntity.badRequest().body(result);
    }
    //PostMan PostRequest URL=http://localhost:8080/teachers/assignToStudent
    //body {
    //    "studentId":1,
    //    "teacherId":5
    //}
}
