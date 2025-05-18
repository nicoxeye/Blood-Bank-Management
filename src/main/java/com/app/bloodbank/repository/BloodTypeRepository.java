package com.app.bloodbank.repository;

import com.app.bloodbank.model.BloodGroup;
import com.app.bloodbank.model.BloodType;
import com.app.bloodbank.model.RhFactor;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloodTypeRepository extends JpaRepository<BloodType, Long> {
    Optional<BloodType> findByBloodGroupAndProtein(BloodGroup group, RhFactor protein);
}
