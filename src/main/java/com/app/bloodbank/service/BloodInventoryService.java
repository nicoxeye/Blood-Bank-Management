package com.app.bloodbank.service;

import com.app.bloodbank.model.BloodInventory;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BloodInventoryService {
    public abstract List<BloodInventory> getAllBloodInventory();
    public abstract BloodInventory getBloodInventoryById(Long id);
    public abstract void addBloodInventory(BloodInventory bloodInventory);
    public abstract void updateBloodInventory(Long id, BloodInventory updatedData);
    public abstract void deleteBloodInventory(Long id);

    void increaseBloodStock(Long inventoryId, double liters);
    void decreaseBloodStock(Long inventoryId, double liters);
    double getAvailableBloodStock(Long bloodBankId, Long bloodTypeId);
}
