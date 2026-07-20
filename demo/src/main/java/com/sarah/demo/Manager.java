package com.sarah.demo;
import java.util.* ;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty ;

@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(unique = true)
    private String email ;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password ;

    @OneToMany(mappedBy = "manager",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Patient> patients  = new ArrayList<>() ;

    public Manager() {}

    public Manager(String email, String password) {
        this.email = email ;
        this.password = password ;
    }
    public long getId() {
        return id ;
    }
    public void setId(long id) {
        this.id = id ;
    }
    public String getEmail() {
        return email ;
    }
    public void setEmail(String email) {
        this.email = email ;
    }
    public String getPassword() {
        return password ;
    }
    public void setPassword(String password) {
        this.password = password ;
    }
    public void setPatients(List<Patient> patients) {
        this.patients = patients ;
    }
    public List<Patient> getPatients() {
        return patients ;
    }

}
