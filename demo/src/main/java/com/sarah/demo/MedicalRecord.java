package com.sarah.demo;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.* ;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String description ;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    private Patient patient;

    public MedicalRecord() {}

    public MedicalRecord(String description, Patient patient) {
        this.description = description ;
        this.patient = patient ;
    }

    public MedicalRecord(MedicalRecord other) {
        this.description = other.description;
        this.patient = other.patient;
    }

    public long getId() {
        return id ;
    }

    public String getDescription() {
        return description ;
    }

    public Patient getPatient() {
        return patient ;
    }

    public void setId(long id) {
        this.id = id ;
    }

    public void setDescription(String description) {
        this.description = description ;
    }

    public void setPatient(Patient patient) {
        this.patient = patient ;
    }
}
