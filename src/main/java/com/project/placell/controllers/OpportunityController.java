package com.project.placell.controllers;

import com.project.placell.models.Opportunity;
import com.project.placell.services.OpportunityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opportunity")
public class OpportunityController {

    @Autowired
    OpportunityServices opportunityServices;

    @GetMapping()
    List<Opportunity> getAllOpportunities(@RequestParam(name="active") boolean active){
        return opportunityServices.getAllOpportunities(active);
    }

    @GetMapping("/{id}")
    ResponseEntity getOpportunity(@PathVariable Long id){
        return opportunityServices.getOpportunityById(id);
    }

    /*
    * To be accessible only to admin
    * */
    @PostMapping()
    Opportunity addNewOpportunity(@RequestBody Opportunity newOpportunity){
        return opportunityServices.addNewOpportunity(newOpportunity);
    }

    /*
     * To be accessible only to admin
     * */
    @PatchMapping("/{id}")
    ResponseEntity editOpportunity(@PathVariable Opportunity updatedOpportunity,@PathVariable Long id){
        return opportunityServices.editOpportunity(updatedOpportunity);
    }

    @PostMapping("/{id}/register/{studentId}")
    ResponseEntity registerStudent(@PathVariable Long id, @PathVariable Long studentId){
        return opportunityServices.registerStudentForOpportunity(id);
    }
}
