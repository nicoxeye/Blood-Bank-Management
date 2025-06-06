package com.app.bloodbank.repository;

import com.app.bloodbank.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonorId(Long donorId);

    @Query("SELECT d FROM Donation d " +
            "JOIN FETCH d.donor " +
            "JOIN FETCH d.bloodBank bb " +
            "LEFT JOIN FETCH bb.bloodInventories bi " +
            "LEFT JOIN FETCH bi.bloodType " +
            "JOIN FETCH d.bloodType")
    List<Donation> findAllWithDonorAndBankAndInventory();

}
