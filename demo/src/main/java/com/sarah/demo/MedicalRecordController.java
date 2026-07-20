package com.sarah.demo;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/records")

public class MedicalRecordController {

    private final PatientRepository patientRepo ;
    private final MedicalRecordRepository recordRepo ;

    public MedicalRecordController(PatientRepository patientRepo, MedicalRecordRepository recordRepo) {
        this.patientRepo = patientRepo ;
        this.recordRepo = recordRepo ;
    }

    @PostMapping("/patient/{patientId}")
    public MedicalRecord addRecord(@PathVariable long patientId, @RequestBody MedicalRecord record, Authentication authentication) {
        String email = authentication.getName();
        Patient patient = patientRepo.findByIdAndManagerEmail(patientId, email).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Patient not found !"));
        record.setPatient(patient);
        return recordRepo.save(record) ;
    }

    @DeleteMapping("/{recordId}")
    public void deleteRecord(@PathVariable long recordId, Authentication authentication) {
        String email = authentication.getName();
        MedicalRecord medicalRecord = recordRepo.findByIdAndPatientManagerEmail(recordId, email).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Medical record not found !")) ;
        recordRepo.delete(medicalRecord);
    }

}
