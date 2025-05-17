package com.app.bloodbank.data;

import com.app.bloodbank.model.BloodType;
import com.app.bloodbank.model.BloodGroup;
import com.app.bloodbank.model.RhFactor;
import com.app.bloodbank.repository.BloodTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(1)
@Component
public class BloodTypeData implements CommandLineRunner {

    private final BloodTypeRepository bloodTypeRepository;

    public BloodTypeData(BloodTypeRepository bloodTypeRepository) {
        this.bloodTypeRepository = bloodTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bloodTypeRepository.count() == 0) {
            List<BloodType> bloodTypes = List.of(
                    new BloodType(BloodGroup.A, RhFactor.POSITIVE),
                    new BloodType(BloodGroup.A, RhFactor.NEGATIVE),
                    new BloodType(BloodGroup.B, RhFactor.POSITIVE),
                    new BloodType(BloodGroup.B, RhFactor.NEGATIVE),
                    new BloodType(BloodGroup.AB, RhFactor.POSITIVE),
                    new BloodType(BloodGroup.AB, RhFactor.NEGATIVE),
                    new BloodType(BloodGroup.O, RhFactor.POSITIVE),
                    new BloodType(BloodGroup.O, RhFactor.NEGATIVE)
            );
            bloodTypeRepository.saveAll(bloodTypes);
            System.out.println("Blood types preloaded to the database");
        }
    }
}
