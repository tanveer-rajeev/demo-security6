package com.rajeeb.indentityservice.controller;

import com.rajeeb.indentityservice.dto.AuthRequest;
import com.rajeeb.indentityservice.entity.UserCredential;
import com.rajeeb.indentityservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/getUser/{username}")
    public UserCredential getUserIdByUsername(@PathVariable String username){
       return authService.getUserIdByName(username);
    }

    @PostMapping("/register")
    public void saveUser(@RequestBody UserCredential userCredential) {
        authService.saveUser(userCredential);
    }

    @GetMapping("/token")
    public String getToken(@RequestBody AuthRequest user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.generateToken(user.getUsername());
        } else throw new RuntimeException("Invalid identity");

    }
//
//    @GetMapping("/validate")
//    public String validateToken(@RequestParam("token") String token) {
//        authService.validateToken(token);
//        return "Token is valid";
//    }
}

