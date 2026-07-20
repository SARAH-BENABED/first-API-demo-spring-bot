package com.sarah.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long>{
    Optional<MedicalRecord> findByIdAndPatientManagerEmail(long id, String email);
}
