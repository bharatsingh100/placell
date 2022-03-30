package com.project.placell.repositories;

import com.project.placell.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    public Optional<Student> findByEmail(String email);
}
