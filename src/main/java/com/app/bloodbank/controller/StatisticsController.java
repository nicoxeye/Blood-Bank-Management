package com.app.bloodbank.controller;

import com.app.bloodbank.model.BloodInventory;
import com.app.bloodbank.repository.BloodInventoryRepository;
import com.app.bloodbank.repository.DonationRepository;
import com.app.bloodbank.repository.DonorRepository;
import com.app.bloodbank.repository.RequestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final BloodInventoryRepository bloodInventoryRepository;
    private final RequestRepository requestRepository;
    private final DonorRepository donorRepository;
    private final DonationRepository donationRepository;

    public StatisticsController(BloodInventoryRepository bloodInventoryRepository, RequestRepository requestRepository, DonorRepository donorRepository, DonationRepository donationRepository) {
        this.bloodInventoryRepository = bloodInventoryRepository;
        this.requestRepository = requestRepository;
        this.donorRepository = donorRepository;
        this.donationRepository = donationRepository;
    }

    @GetMapping("/requests")
    public Map<String, Object> getStatistics2() {
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

    @GetMapping
    public Map<String, Object> getStatistics() {
        long totalDonations = donationRepository.count();
        long totalDonors = donorRepository.count();

        List<BloodInventory> allInventories = bloodInventoryRepository.findAll();

        double totalLiters = allInventories.stream().mapToDouble(BloodInventory::getQuantityInLiters).sum();

        Map<String, Double> inventoryByType = allInventories.stream().collect(Collectors.groupingBy(
                        inv -> inv.getBloodType().toString(),
                        Collectors.summingDouble(BloodInventory::getQuantityInLiters)));


        Map<String, Object> response = new HashMap<>();
        response.put("totalDonations", totalDonations);
        response.put("totalDonors", totalDonors);
        response.put("totalLiters", totalLiters);
        response.put("inventoryByType", inventoryByType);

        return response;
    }

}
