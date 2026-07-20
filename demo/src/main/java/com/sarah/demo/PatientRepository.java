package com.sarah.demo;
import java.util.* ;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByManagerId(long managerId) ;
    List<Patient> findByManagerEmail(String email) ;
    Optional<Patient> findByIdAndManagerEmail(long id, String email) ;

}
