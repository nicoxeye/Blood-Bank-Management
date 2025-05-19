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
    public void updateDonation(Long id, Donor updatedData) {
        Donor existingDonor = donorRepository.findById(id).orElseThrow(() -> new RuntimeException("Donor not found"));

        existingDonor.setBloodType(updatedData.getBloodType());
        existingDonor.setSurname(updatedData.getSurname());
        existingDonor.setAddress(updatedData.getAddress());
        existingDonor.setPhoneNumber(updatedData.getPhoneNumber());
        existingDonor.setGender(updatedData.getGender());
        existingDonor.setName(updatedData.getName());
        existingDonor.setDateOfBirth(updatedData.getDateOfBirth());

    }

    @Override
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }

    @Override
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @Override
    public Donation getDonationById(Long id) {
        return donationRepository.findById(id).orElseThrow(() -> new RuntimeException("Donation not found"));
    }
}
