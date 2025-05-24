package com.app.bloodbank.data;

import com.app.bloodbank.model.*;
import com.app.bloodbank.repository.BloodBankRepository;
import com.app.bloodbank.repository.BloodTypeRepository;
import com.app.bloodbank.repository.HospitalRepository;
import com.app.bloodbank.repository.RequestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Order(6)
@Component
public class RequestData implements CommandLineRunner {

    private final RequestRepository requestRepository;
    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bloodBankRepository;
    private final BloodTypeRepository bloodTypeRepository;

    public RequestData(RequestRepository requestRepository,
                       HospitalRepository hospitalRepository,
                       BloodBankRepository bloodBankRepository,
                       BloodTypeRepository bloodTypeRepository) {
        this.requestRepository = requestRepository;
        this.hospitalRepository = hospitalRepository;
        this.bloodBankRepository = bloodBankRepository;
        this.bloodTypeRepository = bloodTypeRepository;
    }

    @Override
    public void run(String... args) {
        if (requestRepository.count() == 0) {

            List<Hospital> hospitals = hospitalRepository.findAll();
            List<BloodBank> bloodBanks = bloodBankRepository.findAll();
            List<BloodType> bloodTypes = bloodTypeRepository.findAll();

            if (!hospitals.isEmpty() && !bloodBanks.isEmpty() && !bloodTypes.isEmpty()) {
                Request r1 = new Request(hospitals.get(0), bloodBanks.get(0), bloodTypes.get(0), 2.5, LocalDate.now());

                Request r2 = new Request(hospitals.get(2), bloodBanks.get(1), bloodTypes.get(6), 1.0, LocalDate.now().minusDays(1));

                requestRepository.saveAll(List.of(r1, r2));
            }
        }
    }
}