package com.app.bloodbank.repository;

import com.app.bloodbank.model.BloodInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BloodInventoryRepository extends JpaRepository<BloodInventory, Long> {
    @Query("SELECT SUM(bi.quantityInLiters) FROM BloodInventory bi WHERE bi.bloodBank.id = :bloodBankId AND bi.bloodType.id = :bloodTypeId")
    Double getAvailableBloodStock(@Param("bloodBankId") Long bloodBankId, @Param("bloodTypeId") Long bloodTypeId);

    Optional<BloodInventory> findByBloodBankIdAndBloodTypeId(Long bloodBankId, Long bloodTypeId);

    @Query("SELECT SUM(b.quantityInLiters) FROM BloodInventory b")
    Double totalBloodQuantity();

}
