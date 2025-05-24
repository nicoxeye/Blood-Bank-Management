package com.app.bloodbank.repository;

import com.app.bloodbank.model.Donor;
import com.app.bloodbank.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> getPatientById(Long id);

    List<Patient> findByNameContainingIgnoreCase(String name);

    @Query("SELECT d FROM Donor d WHERE d.address.city = :city")
    List<Patient> findByCity(@Param("city") String city);

}
