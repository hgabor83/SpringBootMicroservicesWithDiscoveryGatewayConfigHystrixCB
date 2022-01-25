package com.example.student.controller;

import com.example.student.VO.ResponseTemplateVO;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/")
    public Student saveStudent(@RequestBody Student newStudent){
        log.info("Save a student");
        return studentService.save(newStudent);
    }

    /* Original getStudent
    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable("id") Integer id){
        log.info("Find a student by id");
        return studentService.findStudentById(id);
    }
     */

    // The 2 services together
    @GetMapping("/{id}")
    public ResponseTemplateVO getStudentWithSchool(@PathVariable("id") Integer studentId){
        log.info("Get a student with school by student id");
        return studentService.getStudentWithSchool(studentId);
    }
}
