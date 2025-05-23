package com.app.bloodbank.service;

import com.app.bloodbank.model.BloodBank;
import com.app.bloodbank.model.Donation;
import com.app.bloodbank.model.Donor;
import com.app.bloodbank.repository.BloodBankRepository;
import com.app.bloodbank.repository.DonationRepository;
import com.app.bloodbank.repository.DonorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;
    private final BloodBankRepository bloodBankRepository;

    public DonationServiceImpl(DonationRepository donationRepository, DonorRepository donorRepository, BloodBankRepository bloodBankRepository) {
        this.donationRepository = donationRepository;
        this.donorRepository = donorRepository;
        this.bloodBankRepository = bloodBankRepository;
    }

    @Override
    public void registerDonation(LocalDate date, Long donorId, Long bloodBankId) {
        Donor donor = donorRepository.findById(donorId).orElseThrow(() -> new RuntimeException("Donor not found"));

        BloodBank bloodBank = bloodBankRepository.findById(bloodBankId).orElseThrow(() -> new RuntimeException("BloodBank not found"));

        Donation donation = new Donation(date, donor, bloodBank, donor.getBloodType());
        donationRepository.save(donation);
    }

    @Override
    public void registerDonation(Long donorId, Long bloodBankId) {
        Donor donor = donorRepository.findById(donorId).orElseThrow(() -> new RuntimeException("Donor not found"));

        BloodBank bloodBank = bloodBankRepository.findById(bloodBankId).orElseThrow(() -> new RuntimeException("BloodBank not found"));

        Donation donation = new Donation(LocalDate.now(), donor, bloodBank, donor.getBloodType());
        donationRepository.save(donation);
    }

    @Transactional
    @Override
    public void updateDonation(Long id, Donation updatedData) {
        Donation existingDonation = getDonationById(id);

        existingDonation.setBloodType(updatedData.getBloodType());
        existingDonation.setDonor(updatedData.getDonor());
        existingDonation.setBloodBank(updatedData.getBloodBank());
        existingDonation.setDate(updatedData.getDate());

    }

    @Override
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }


    //TODO
    @Override
    public Donation getDonationById(Long id) {
        return donationRepository.findById(id).orElseThrow(() -> new RuntimeException("Donation not found"));
    }

    @Override
    public List<Donation> getDonationsByDonorId(Long donorId) {
        return List.of();
    }
}
