package com.app.bloodbank.repository;

import com.app.bloodbank.model.BloodGroup;
import com.app.bloodbank.model.Donor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    List<Donor> findByNameContainingIgnoreCase(String name);

    @Query("SELECT d FROM Donor d WHERE d.address.city = :city")
    List<Donor> findByCity(@Param("city") String city);

}
