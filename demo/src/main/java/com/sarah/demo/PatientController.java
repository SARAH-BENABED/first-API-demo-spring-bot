package com.sarah.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/patients")

public class PatientController {

    private final PatientRepository repo ;
    private final ManagerRepository managerRepo ;

    public PatientController(PatientRepository repo, ManagerRepository managerRepo) {
        this.repo = repo ;
        this.managerRepo = managerRepo ;
    }

    @GetMapping()
    public List<Patient> getPatient(Authentication authentication) {
        String email = authentication.getName() ;
        return repo.findByManagerEmail(email) ;
    }

    @PostMapping()
    public Patient addPatient(@RequestBody Patient patient, Authentication authentication) {
        String email = authentication.getName() ;
        Manager manager = managerRepo.findByEmail(email).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Manager not found !")) ;
        patient.setManager(manager);
        return repo.save(patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable long id, Authentication authentication) {
        String email = authentication.getName() ;
        Patient patient = repo.findByIdAndManagerEmail(id, email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Patient not found")) ;
        repo.delete(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable long id , @RequestBody Patient updatedPatient, Authentication authentication) {
        String email = authentication.getName() ;
        Patient patient = repo.findByIdAndManagerEmail(id, email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Patient not found")) ;
        updatedPatient.setId(patient.getId());
        updatedPatient.setManager(patient.getManager());
        return repo.save(updatedPatient) ;
    }

}
