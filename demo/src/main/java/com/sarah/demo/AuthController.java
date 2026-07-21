package com.sarah.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException ;
import io.jsonwebtoken.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")

public class AuthController {

    private final ManagerRepository managerRepo ;
    private final JwtService jwtService;

    public AuthController(ManagerRepository managerRepo,  JwtService jwtService) {
        this.managerRepo = managerRepo ;
        this.jwtService = jwtService;
    }
    @GetMapping("/hello")
    public String hello() {
        return "Backend is running!";
    }

    @PostMapping("/register")
    public Manager register(@RequestBody Manager manager) {
        if((managerRepo.findByEmail(manager.getEmail())).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists") ;
        }

        return managerRepo.save(manager) ;
    }

    @PostMapping("/login")
    public String login(@RequestBody Manager manager) {
        Manager existing = managerRepo.findByEmail(manager.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password")) ;
        if(!existing.getPassword().equals(manager.getPassword())) {
            throw new  ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password");
        }
        String token = jwtService.generateToken(existing.getEmail()) ;
        return token ;
    }

}
