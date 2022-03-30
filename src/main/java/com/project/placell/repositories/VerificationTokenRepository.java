package com.project.placell.repositories;

import com.project.placell.models.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<EmailVerificationToken,Long> {
    Optional<EmailVerificationToken> findByToken(String token);
}
