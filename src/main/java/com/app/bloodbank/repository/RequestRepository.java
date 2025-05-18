package com.app.bloodbank.repository;


import com.app.bloodbank.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
