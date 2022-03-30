package com.project.placell.repositories;

import com.project.placell.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {

    @Query("SELECT o FROM opportunity WHERE o.active = true")
    List<Opportunity> findAllActive();
}
