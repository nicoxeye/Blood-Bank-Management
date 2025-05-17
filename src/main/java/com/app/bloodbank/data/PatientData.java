package com.app.bloodbank.data;

import com.app.bloodbank.model.*;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.repository.BloodTypeRepository;
import com.app.bloodbank.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Order(2)
@Component
public class PatientData implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final AddressRepository addressRepository;
    private final BloodTypeRepository bloodTypeRepository;

    public PatientData(PatientRepository patientRepository,
                       AddressRepository addressRepository,
                       BloodTypeRepository bloodTypeRepository) {
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
        this.bloodTypeRepository = bloodTypeRepository;
    }

    @Override
    public void run(String... args){
        if (patientRepository.count() == 0) {
            // temporary sample patients

            Address address1 = new Address("USA", "New York", "5th Avenue", "23900");
            Address address2 = new Address("Germany", "Berlin", "Unter den Linden", "49302");

            addressRepository.saveAll(List.of(address1, address2));

            // loading blood types from the database because they're constant and filled already
            Optional<BloodType> aPositive = bloodTypeRepository.findByBloodGroupAndProtein(BloodGroup.A, RhFactor.POSITIVE);
            Optional<BloodType> oNegative = bloodTypeRepository.findByBloodGroupAndProtein(BloodGroup.O, RhFactor.NEGATIVE);

            if (aPositive.isEmpty() || oNegative.isEmpty()) {
                System.out.println("Required blood types not found in the DataBase.");
                return;
            }

            Patient p1 = new Patient("Jane", "Smith", aPositive.get(), LocalDate.of(1995, 3, 12), "F", address1, "123456789");

            Patient p2 = new Patient("Bob", "Schmidt", oNegative.get(), LocalDate.of(1988, 7, 4), "M", address2, "987654321");

            patientRepository.saveAll(List.of(p1, p2));
            System.out.println("Sample patients loaded.");
        }
    }
}
