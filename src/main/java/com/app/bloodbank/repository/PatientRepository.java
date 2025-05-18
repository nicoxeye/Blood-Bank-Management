package com.app.bloodbank.repository;

import com.app.bloodbank.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> getPatientById(Long id);
}
