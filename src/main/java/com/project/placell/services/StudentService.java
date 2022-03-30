package com.project.placell.services;

import com.project.placell.models.Student;
import com.project.placell.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity addStudent(Student newStudent){
        try{
            studentRepository.save(newStudent);
            return new ResponseEntity("Student added successfully", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity("some error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public ResponseEntity findById(Long id){
        try{
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if(!optionalStudent.isPresent()){
                return new ResponseEntity("No student found for the given Id",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(optionalStudent.get(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity("some error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity updateStudent(Student updatedStudent){
        try{
            Optional<Student> optionalStudent = studentRepository.findById(updatedStudent.getId());
            if(!optionalStudent.isPresent()){
                return new ResponseEntity("No student found for the given Id",HttpStatus.NOT_FOUND);
            }
            studentRepository.save(updatedStudent);
            return new ResponseEntity(updatedStudent,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity("some error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity uploadResume(Long id){
        try{
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if(!optionalStudent.isPresent()){
                return new ResponseEntity("No student found for the given Id",HttpStatus.NOT_FOUND);
            }
            Student updatedStudent = optionalStudent.get();
            /*
            * Storage service code goes here
            * then change the current student object
            * and then save it to repository
            * */
            return new ResponseEntity("success",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity("some error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
