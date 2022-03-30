package com.project.placell.services;

import com.project.placell.models.Opportunity;
import com.project.placell.models.Student;
import com.project.placell.repositories.OpportunityRepository;
import com.project.placell.repositories.StudentRepository;
import com.project.placell.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OpportunityServices {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    StudentRepository studentRepository;

    public List<Opportunity> getAllOpportunities(boolean active){

        if(active) return opportunityRepository.findAllActive();

        return opportunityRepository.findAll();
    }

    public Opportunity addNewOpportunity(Opportunity newOpportunity){
        return opportunityRepository.save(newOpportunity);
    }

    public ResponseEntity getOpportunityById(Long id){
        try {
            Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(id);
            if(optionalOpportunity.isEmpty()){
                return new ResponseEntity<>("Opportunity not found",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(optionalOpportunity.get(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity("Some error Occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity editOpportunity(Opportunity updatedOpportunity){
        try {
            Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(updatedOpportunity.getId());
            if(optionalOpportunity.isEmpty()){
                return new ResponseEntity("Opportunity not found", HttpStatus.NOT_FOUND);
            }
            opportunityRepository.save(updatedOpportunity);
            return new ResponseEntity("Successfully updated opportunity",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity("Some Error Occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
    * To register a student to an open Opportunity
    * */
    public ResponseEntity registerStudentForOpportunity(Long id){
        try{
            Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(id);
            if(optionalOpportunity.isEmpty()){
                return  new ResponseEntity("opportunity not found with the id "+id,HttpStatus.NOT_FOUND);
            }

            Opportunity opportunity = optionalOpportunity.get();
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            Student student = studentRepository.findByEmail(userDetails.getUsername()).get();

            //check if student with the given email(username) exists or not
            if(student == null){
               return new ResponseEntity("student not found with email"+userDetails.getUsername(),
                       HttpStatus.NOT_FOUND);
            }

            //Check if opportunity is open or not
            if(!opportunity.isActive()){
                return new ResponseEntity<>("registration is closed for this opportunity",
                        HttpStatus.NOT_ACCEPTABLE);
            }
            opportunity.registerOne(student);
            return new ResponseEntity("successfully registered in the specified opportunity",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity("Some Error Occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
