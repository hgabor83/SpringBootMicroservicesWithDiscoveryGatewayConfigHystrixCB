package com.example.student.controller;

import com.example.student.VO.ResponseTemplateVO;
import com.example.student.VO.School;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class StudentControllerTest {

    //Service mock will be injected to the controller
    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Test
    public void testGetStudentWithSchool() {
        Student student = new Student(1, "Joe", 1);
        School school = new School(1, "School1");
        ResponseTemplateVO studentWithSchool = new ResponseTemplateVO(student, school);
        when(studentService.getStudentWithSchool(any())).thenReturn(studentWithSchool);

        ResponseTemplateVO resultStudentWithSchool = studentController.getStudentWithSchool(Integer.valueOf("1"));
        assertEquals("Joe", resultStudentWithSchool.getStudent().getName());
        assertEquals(1, resultStudentWithSchool.getStudent().getSchoolId());
        assertEquals(1, resultStudentWithSchool.getSchool().getId());
        assertEquals("School1", resultStudentWithSchool.getSchool().getName());
    }

}