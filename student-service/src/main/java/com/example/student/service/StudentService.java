package com.example.student.service;

import com.example.student.VO.ResponseTemplateVO;
import com.example.student.VO.School;
import com.example.student.entity.Student;
import com.example.student.error.StudentNotFoundException;
import com.example.student.helpers.LogExecutionTime;
import com.example.student.repository.StudentRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @LogExecutionTime
    public Student save(Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @LogExecutionTime
    public Student findStudentById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("No such id"));
    }

    @SneakyThrows
    @LogExecutionTime
    @HystrixCommand(fallbackMethod = "getFallbackStudentWithSchool")
    public ResponseTemplateVO getStudentWithSchool(Integer studentId) {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        //For multiple services better way to inject it loadbalanced
        //RestTemplate restTemplate=new RestTemplate();

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("No such student id"));
        //School school = restTemplate.getForObject("http://localhost:8081/schools/" + student.getSchoolId(), School.class);
        //This modification can be done because of Eureka Discovery Service
        School school = restTemplate.getForObject("http://SCHOOL-SERVICE/schools/" + student.getSchoolId(), School.class);

        responseTemplateVO.setStudent(student);
        responseTemplateVO.setSchool(school);

        return responseTemplateVO;
    }

    // In case SCHOOL-SERVICE is down
    @SneakyThrows
    @LogExecutionTime
    public ResponseTemplateVO getFallbackStudentWithSchool(Integer studentId) {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        //For multiple services better way to inject it loadbalanced
        //RestTemplate restTemplate=new RestTemplate();

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("No such student id"));
        //School school = restTemplate.getForObject("http://localhost:8081/schools/" + student.getSchoolId(), School.class);
        //This modification can be done because of Eureka Discovery Service
        School school = new School(0,"Default");

        responseTemplateVO.setStudent(student);
        responseTemplateVO.setSchool(school);

        return responseTemplateVO;
    }
}
