package com.rajeeb.indentityservice.service;

import com.rajeeb.indentityservice.entity.UserCredential;
import com.rajeeb.indentityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    public UserCredential getUserIdByName(String username){
        Optional<UserCredential> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent()){
            return byUsername.get();
        }else throw new RuntimeException("User name not found");
    }

    public String saveUser(UserCredential user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("User name already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "save user";
    }

    public String generateToken(String username){
        return jwtService.generateToken(username);
    }

//    public void validateToken(String token){
//        jwtService.validateToken(token);
//    }


}
