package com.app.bloodbank.service;

import com.app.bloodbank.model.BloodInventory;
import com.app.bloodbank.repository.BloodBankRepository;
import com.app.bloodbank.repository.BloodInventoryRepository;
import com.app.bloodbank.repository.BloodTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodInventoryServiceImpl implements BloodInventoryService {

    private final BloodInventoryRepository bloodInventoryRepository;

    public BloodInventoryServiceImpl(BloodInventoryRepository bloodInventoryRepository) {
        this.bloodInventoryRepository = bloodInventoryRepository;
    }

    @Override
    public List<BloodInventory> getAllBloodInventory() {
        return bloodInventoryRepository.findAll();
    }

    @Override
    public BloodInventory getBloodInventoryById(Long id) {
        return bloodInventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("BloodBank not found"));
    }

    @Transactional
    @Override
    public void increaseBloodStock(Long inventoryId, double liters) {
        BloodInventory bloodInventory = getBloodInventoryById(inventoryId);
        bloodInventory.setQuantityInLiters(bloodInventory.getQuantityInLiters() + liters);

    }

    @Transactional
    @Override
    public void decreaseBloodStock(Long inventoryId, double liters) {
        BloodInventory bloodInventory = getBloodInventoryById(inventoryId);
        bloodInventory.setQuantityInLiters(bloodInventory.getQuantityInLiters() - liters);
        if (bloodInventory.getQuantityInLiters() == 0) {
            bloodInventory.setQuantityInLiters(0);
        }
    }

    @Override
    public double getAvailableBloodStock(Long bloodBankId, Long bloodTypeId) {
        return bloodInventoryRepository.getAvailableBloodStock(bloodBankId, bloodTypeId);
    }

}
