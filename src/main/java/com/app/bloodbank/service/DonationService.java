package com.app.bloodbank.service;

import com.app.bloodbank.model.Donation;
import com.app.bloodbank.model.Donor;

import java.time.LocalDate;
import java.util.List;

public interface DonationService {
    // with date parameter, to register older donations
    public abstract void registerDonation(LocalDate date, Long donorId, Long bloodBankId);

    // with localdate.now()
    public abstract void registerDonation(Long donorId, Long bloodBankId);

    public abstract void updateDonation(Long id, Donation updatedData);
    public abstract List<Donation> getAllDonations();

    public abstract Donation getDonationById(Long id);
    public abstract List<Donation> getDonationsByDonorId(Long donorId);
}
