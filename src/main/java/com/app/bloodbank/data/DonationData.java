package com.app.bloodbank.data;

import com.app.bloodbank.model.Address;
import com.app.bloodbank.model.BloodBank;
import com.app.bloodbank.model.Donation;
import com.app.bloodbank.model.Donor;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.repository.BloodBankRepository;
import com.app.bloodbank.repository.DonationRepository;
import com.app.bloodbank.repository.DonorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Order(4)
@Component
public class DonationData implements CommandLineRunner {

    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;
    private final BloodBankRepository bloodBankRepository;
    private final AddressRepository addressRepository;

    public DonationData(DonationRepository donationRepository, DonorRepository donorRepository, BloodBankRepository bloodBankRepository, AddressRepository addressRepository) {
        this.donationRepository = donationRepository;
        this.donorRepository = donorRepository;
        this.bloodBankRepository = bloodBankRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (donationRepository.count() == 0) {
            Donor donor = donorRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("Donor with ID 1 not found"));

            Address address = new Address("Poland", "Warszawa", "3 Maja", "20300");
            addressRepository.save(address);

            BloodBank bloodBank = new BloodBank("MedicBloodBank", address);
            bloodBankRepository.save(bloodBank);

            Donation donation = new Donation(LocalDate.now(), donor, bloodBank, donor.getBloodType());
            donationRepository.save(donation);

            System.out.println("Sample donation saved.");
        }
    }
}
