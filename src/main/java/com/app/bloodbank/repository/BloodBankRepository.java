package com.app.bloodbank.repository;

import com.app.bloodbank.model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
    List<BloodBank> findByAddress_City(String city);
}
