package com.app.bloodbank.repository;

import com.app.bloodbank.model.BloodBank;
import com.app.bloodbank.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByAddress_City(String city);

}
