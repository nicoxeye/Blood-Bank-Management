package com.app.bloodbank.controller;

import com.app.bloodbank.model.Address;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressRepository addressRepository;

    public AddressController(AddressService addressService, AddressRepository addressRepository) {
        this.addressService = addressService;
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public List<Address> findAll() {
        return addressService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id){
        addressRepository.deleteById(id);
        return ResponseEntity.ok("Address " + id + " deleted");
    }

}
