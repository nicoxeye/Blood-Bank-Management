package com.app.bloodbank.controller;

import com.app.bloodbank.model.BloodInventory;
import com.app.bloodbank.model.Request;
import com.app.bloodbank.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hospitalstatistics")
public class HospitalStatisticsController {

    private final PatientRepository patientRepository;
    private final RequestRepository requestRepository;

    public HospitalStatisticsController(PatientRepository patientRepository, RequestRepository requestRepository) {
        this.patientRepository = patientRepository;
        this.requestRepository = requestRepository;
    }

    @GetMapping
    public Map<String, Object> getStatistics() {
        long totalPatients = patientRepository.count();
        long totalRequests = requestRepository.count();

        List<Object[]> requestStatusCounts = requestRepository.countRequestsByStatus();

        Map<String, Long> requestsByStatus = new HashMap<>();

        for (Object[] row : requestStatusCounts) {
            requestsByStatus.put(row[0].toString(), (Long) row[1]);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalPatients", totalPatients);
        response.put("totalRequests", totalRequests);
        response.put("requestStats", requestsByStatus);

        return response;
    }

}
