package com.app.bloodbank.controller;

import com.app.bloodbank.model.Address;
import com.app.bloodbank.model.BloodBank;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.service.BloodBankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bloodbanks")
public class BloodBankController {

    private final BloodBankService bloodbankService;
    private final AddressRepository addressRepository;

    public BloodBankController(BloodBankService bloodbankService, AddressRepository addressRepository) {
        this.bloodbankService = bloodbankService;
        this.addressRepository = addressRepository;
    }

    @GetMapping("")
    public List<BloodBank> getAllBloodBanks() {
        return bloodbankService.getAllBloodBanks();
    }

    @GetMapping("/{id}")
    public BloodBank getBloodBankById(@PathVariable Long id) {
        return bloodbankService.getBloodBankById(id);
    }

    @PostMapping()
    public ResponseEntity<String> createBloodBank(@RequestBody BloodBank bloodbank) {

        Address incomingAddress = bloodbank.getAddress();

        // findinf an address with the same metadata
        Optional<Address> existingAddress = addressRepository.findByCountryAndCityAndStreetAndZipcode(
                        incomingAddress.getCountry(),
                        incomingAddress.getCity(),
                        incomingAddress.getStreet(),
                        incomingAddress.getZipcode());

        Address finalAddress = existingAddress.orElseGet(() -> addressRepository.save(incomingAddress));

        bloodbank.setAddress(finalAddress);

        bloodbankService.addBloodBank(bloodbank);

        return ResponseEntity.ok("Blood bank created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBloodBank(@RequestBody BloodBank updatedData, @PathVariable Long id) {

        Address incomingAddress = updatedData.getAddress();

        // findinf an address with the same metadata
        Optional<Address> existingAddress = addressRepository.findByCountryAndCityAndStreetAndZipcode(
                incomingAddress.getCountry(),
                incomingAddress.getCity(),
                incomingAddress.getStreet(),
                incomingAddress.getZipcode());

        Address finalAddress = existingAddress.orElseGet(() -> addressRepository.save(incomingAddress));

        updatedData.setAddress(finalAddress);

        bloodbankService.updateBloodBank(id, updatedData);

        return ResponseEntity.ok("Blood bank updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBloodBank(@PathVariable Long id) {
        bloodbankService.deleteBloodBank(id);
        return ResponseEntity.ok("Blood bank deleted");
    }

    @GetMapping("/city/{city}")
    public List<BloodBank> findBloodBankByCity(@PathVariable String city) {
        city = city.toUpperCase();
        return bloodbankService.findBloodBankByCity(city);
    }



}
