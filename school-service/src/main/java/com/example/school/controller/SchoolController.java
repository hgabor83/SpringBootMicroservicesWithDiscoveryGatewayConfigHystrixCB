package com.example.school.controller;

import com.example.school.entity.School;
import com.example.school.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schools")
@Slf4j
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping("/")
    public School saveSchool(@RequestBody School newSchool){
        log.info("Save a new school");
        return schoolService.saveSchool(newSchool);
    }

    @GetMapping("/{id}")
    public School findSchoolById(@PathVariable("id") Integer id){
        log.info("Find a school by id");
        return schoolService.findSchoolById(id);
    }
}
