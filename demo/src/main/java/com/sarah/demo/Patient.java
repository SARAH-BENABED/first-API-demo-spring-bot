package com.sarah.demo;
import java.util.* ;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;


@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String name;
    private int age;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<MedicalRecord> records  = new ArrayList<>() ;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonBackReference
    private Manager manager ;

    // Constructor
    public Patient(String name, int age) {
        this.name = name;
        this.age = age;

    }
    public Patient() {
    }

    // Getters (VERY IMPORTANT)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getId() { return id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(long id) {
        this.id = id ;
    }

    public void setManager(Manager manager) {
        this.manager = manager ;
    }
    public Manager getManager() {
        return manager ;
    }
    public List<MedicalRecord> getRecords() {
        return records ;
    }
    public void setRecords(List<MedicalRecord> records) {
        this.records = records ;
    }
}
