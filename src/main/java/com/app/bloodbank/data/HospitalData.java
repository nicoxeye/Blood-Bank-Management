package com.app.bloodbank.data;

import com.app.bloodbank.model.Address;
import com.app.bloodbank.model.Hospital;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.repository.HospitalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(5)
@Component
public class HospitalData implements CommandLineRunner {

    private final HospitalRepository hospitalRepository;
    private final AddressRepository addressRepository;

    public HospitalData(HospitalRepository hospitalRepository, AddressRepository addressRepository) {
        this.hospitalRepository = hospitalRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (hospitalRepository.count() == 0) {
            List<Address> addresses = addressRepository.findAll();

            hospitalRepository.saveAll(List.of(
                    new Hospital("City Hospital", addresses.get(1), "contact@cityhospital.com", "+123456789"),
                    new Hospital("General Hospital", addresses.get(2), "info@generalhospital.com", "+987654321"),
                    new Hospital("Healthcare Center", addresses.get(3), "support@healthcare.com", "+1122334455")
            ));
        }
    }
}
