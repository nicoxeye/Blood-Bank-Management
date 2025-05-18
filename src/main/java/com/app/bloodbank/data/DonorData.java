package com.app.bloodbank.data;

import com.app.bloodbank.repository.DonorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class DonorData implements CommandLineRunner {

    private final DonorRepository donorRepository;

    public DonorData(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
