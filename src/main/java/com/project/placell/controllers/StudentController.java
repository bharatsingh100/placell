package com.project.placell.controllers;

import com.project.placell.models.Student;
import com.project.placell.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("")
    List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @PostMapping("")
    ResponseEntity addStudent(@RequestBody Student newStudent){
        return studentService.addStudent(newStudent);
    }

    @GetMapping("/{id}")
    ResponseEntity getStudentById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @PatchMapping("/{id}")
    ResponseEntity updateStudentInfo(@RequestBody Student updatedStudent){
        return studentService.updateStudent(updatedStudent);
    }
}
