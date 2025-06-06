package com.app.bloodbank.repository;

import com.app.bloodbank.model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
    List<BloodBank> findByAddress_City(String city);

    @Query("SELECT COUNT(d) FROM Donation d WHERE d.bloodBank.id = :bloodBankId")
    Long countDonationsByBloodBankId(@Param("bloodBankId") Long bloodBankId);

}
