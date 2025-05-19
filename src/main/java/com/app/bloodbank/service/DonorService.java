package com.app.bloodbank.service;

import com.app.bloodbank.model.Donor;
import java.util.List;

public interface DonorService {
    public abstract void addDonor(Donor donor);
    public abstract void updateDonor(Long id, Donor updatedData);
    public abstract void deleteDonor(Long id);
    public abstract List<Donor> getAllDonors();
    public abstract Donor getDonorById(Long id);
}
