package org.example.cusotmerandaddress.controller;

import org.example.cusotmerandaddress.entity.Address;
import org.example.cusotmerandaddress.service.address.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    //Create
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.save(address));
    }

    //Read All
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.findAll());
    }

    //Read BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        return ResponseEntity.ok(addressService.update(id, address));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
