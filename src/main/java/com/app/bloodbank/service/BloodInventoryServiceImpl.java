package com.app.bloodbank.service;

import com.app.bloodbank.model.BloodInventory;
import com.app.bloodbank.repository.BloodBankRepository;
import com.app.bloodbank.repository.BloodInventoryRepository;
import com.app.bloodbank.repository.BloodTypeRepository;
import jakarta.transaction.Transactional;

import java.util.List;

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

    @Override
    public void addBloodInventory(BloodInventory bloodInventory) {
        bloodInventoryRepository.save(bloodInventory);
    }

    @Transactional
    @Override
    public void updateBloodInventory(Long id, BloodInventory updatedData) {
        BloodInventory existingInventory = getBloodInventoryById(id);

        existingInventory.setBloodBank(updatedData.getBloodBank());
        existingInventory.setBloodBank(updatedData.getBloodBank());
        existingInventory.setQuantityInLiters(updatedData.getQuantityInLiters());
    }

    @Override
    public void deleteBloodInventory(Long id) {
        bloodInventoryRepository.deleteById(id);
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
