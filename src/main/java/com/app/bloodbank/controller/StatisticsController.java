package com.app.bloodbank.controller;

import com.app.bloodbank.repository.BloodInventoryRepository;
import com.app.bloodbank.repository.DonorRepository;
import com.app.bloodbank.repository.RequestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final BloodInventoryRepository bloodInventoryRepository;
    private final RequestRepository requestRepository;

    public StatisticsController(BloodInventoryRepository bloodInventoryRepository, RequestRepository requestRepository) {
        this.bloodInventoryRepository = bloodInventoryRepository;
        this.requestRepository = requestRepository;
    }

    @GetMapping
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();

        Double totalBlood = bloodInventoryRepository.totalBloodQuantity();
        stats.put("totalBloodInInventory", totalBlood != null ? totalBlood : 0);

        List<Object[]> requestStatusCounts = requestRepository.countRequestsByStatus();

        Map<String, Long> requestsByStatus = new HashMap<>();

        for (Object[] row : requestStatusCounts) {
            requestsByStatus.put(row[0].toString(), (Long) row[1]);
        }

        stats.put("requestsByStatus", requestsByStatus);

        return stats;
    }
}
