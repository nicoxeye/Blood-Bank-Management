package com.app.bloodbank.repository;

import com.app.bloodbank.model.BloodInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodInventoryRepository extends JpaRepository<BloodInventory, Long> {
}
