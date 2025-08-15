package org.example.cusotmerandaddress.service.customer;

import org.example.cusotmerandaddress.entity.Customer;
import org.example.cusotmerandaddress.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (customer.getAddresss() != null) {
            customer.getAddresss().forEach(addr -> addr.setCustomer(customer));
        }

        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Customer not found with this id :" + id));
    }

    @Transactional
    @Override
    public Customer updateCustomer(Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(updatedCustomer.getId())
                .orElseThrow(()
                        -> new RuntimeException("Customer not found with this id : " + updatedCustomer.getId()));

        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setDateOfBirth(updatedCustomer.getDateOfBirth());

        if (updatedCustomer.getAddresss() != null) {
            existingCustomer.getAddresss().clear();
            updatedCustomer.getAddresss().forEach(addr -> addr.setCustomer(existingCustomer));
            existingCustomer.getAddresss().addAll(updatedCustomer.getAddresss());
        }

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
