package com.app.bloodbank.repository;


import com.app.bloodbank.model.Request;
import com.app.bloodbank.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByBloodBankIdAndBloodTypeIdAndStatus(Long bankId, Long typeId, Status status);

    @Query("SELECT r.status, COUNT(r) FROM Request r GROUP BY r.status")
    List<Object[]> countRequestsByStatus();

}
