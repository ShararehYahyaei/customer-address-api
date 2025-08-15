package org.example.cusotmerandaddress.service.address;

import org.example.cusotmerandaddress.entity.Address;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressService {

    Address save(Address address);

    List<Address> findAll();

    Address findById(Long id);

    void deleteById(Long id);


    @Transactional
    Address update(Long id, Address updatedAddress);
}
