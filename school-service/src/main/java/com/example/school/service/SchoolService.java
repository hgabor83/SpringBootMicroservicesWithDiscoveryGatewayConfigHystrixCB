package com.example.school.service;

import com.example.school.entity.School;
import com.example.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public School saveSchool(School newSchool) {
        return schoolRepository.save(newSchool);
    }

    public School findSchoolById(Integer id) {
        return schoolRepository.findById(id).orElseThrow(()->new RuntimeException("No such id"));
    }
}
