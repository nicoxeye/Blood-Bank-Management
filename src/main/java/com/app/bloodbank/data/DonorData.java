package com.app.bloodbank.data;

import com.app.bloodbank.model.*;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.repository.BloodTypeRepository;
import com.app.bloodbank.repository.DonorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Order(3)
@Component
public class DonorData implements CommandLineRunner {

    private final DonorRepository donorRepository;
    private final AddressRepository addressRepository;
    private final BloodTypeRepository bloodTypeRepository;

    public DonorData(DonorRepository donorRepository, AddressRepository addressRepository, BloodTypeRepository bloodTypeRepository) {
        this.donorRepository = donorRepository;
        this.addressRepository = addressRepository;
        this.bloodTypeRepository = bloodTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (donorRepository.count() == 0) {
            // temporary sample donors

            Address address1 = new Address("United Kingdom", "London", "Oxford Street", "45890");
            Address address2 = new Address("Italy", "Florence", "Via Maggio", "93475");

            addressRepository.saveAll(List.of(address1, address2));

            Optional<BloodType> abPositive = bloodTypeRepository.findByBloodGroupAndProtein(BloodGroup.AB, RhFactor.POSITIVE);
            Optional<BloodType> oPositive = bloodTypeRepository.findByBloodGroupAndProtein(BloodGroup.O, RhFactor.POSITIVE);

            if (abPositive.isEmpty() || oPositive.isEmpty()) {
                System.out.println("Required blood types not found in the DataBase.");
                return;
            }

            Donor d1 = new Donor("Leo", "Brown", abPositive.get(), LocalDate.of(1990, 1, 28), "M", address1, "345283749");

            Donor d2 = new Donor("Alice", "White", oPositive.get(), LocalDate.of(2002, 12, 19), "O", address2, "647785939");

            donorRepository.saveAll(List.of(d1, d2));
            System.out.println("Sample donors loaded.");
        }
    }
}
