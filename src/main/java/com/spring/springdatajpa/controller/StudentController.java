package com.spring.springdatajpa.controller;

import com.spring.springdatajpa.dto.StudentDTO;
import com.spring.springdatajpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<StudentDTO> getByEmail(@PathVariable String emailId) {
        return studentService.getStudent(emailId);
    }
}
