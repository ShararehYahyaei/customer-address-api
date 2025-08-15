package org.example.cusotmerandaddress.service.address;

import org.example.cusotmerandaddress.entity.Address;
import org.example.cusotmerandaddress.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address Not found"));

    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Address update(Long id, Address updatedAddress) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        address.setStreet(updatedAddress.getStreet());
        address.setCity(updatedAddress.getCity());
        address.setState(updatedAddress.getState());
        address.setCountry(updatedAddress.getCountry());
        address.setPostalCode(updatedAddress.getPostalCode());

        return addressRepository.save(address);
    }
}
