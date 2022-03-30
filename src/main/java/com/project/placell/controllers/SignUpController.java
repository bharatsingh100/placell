package com.project.placell.controllers;

import com.project.placell.models.EmailVerificationToken;
import com.project.placell.models.SignupDetails;
import com.project.placell.models.User;
import com.project.placell.repositories.UserRepository;
import com.project.placell.repositories.VerificationTokenRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationTokenRepository tokenRepository;

    @Autowired
    JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    String senderMail;

    @Value("${application.url}")
    String serverURl;

    @PostMapping()
    public ResponseEntity registerStudent(@RequestBody SignupDetails signupDetails){
        try{
            Optional<User> optionalUser = userRepository.findByEmail(signupDetails.getEmail());
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                if(user.isActive()){
                    return new ResponseEntity("User already exists, sign in to proceed", HttpStatus.OK);
                }
                return ResponseEntity.ok("User already exists, confirm your email to activate your account");
            }

            String generatedString = RandomStringUtils.randomAlphabetic(10);
            EmailVerificationToken token = new EmailVerificationToken(generatedString,signupDetails.getEmail());
            tokenRepository.save(token);

            User user = new User(signupDetails.getEmail(),signupDetails.getPassword(),false,"STUDENT");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderMail);
            message.setTo(signupDetails.getEmail());
            message.setSubject("account activation link");
            message.setText("here is your account activation link, click the link below\n"+
                    serverURl+"/signup/confirm-email?token="+token.getToken());
            emailSender.send(message);

            User createdUser = userRepository.save(user);
            return new ResponseEntity(createdUser,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity("some error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/confirm-email")
    public ResponseEntity confirmEmail(@RequestParam(name="token") String token){
        try{
            Optional<EmailVerificationToken> optionalToken = tokenRepository.findByToken(token);
            if(optionalToken.isPresent()){
                EmailVerificationToken verificationToken = optionalToken.get();
                User user = userRepository.findByEmail(verificationToken.getUserEmail()).get();
                user.setActive(true);
                userRepository.save(user);
                return ResponseEntity.ok("Your email has been verified, and your account is now active");
            }
            return new ResponseEntity("invalid token",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity("some error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
