package org.example.cusotmerandaddress.service.customer;

import org.example.cusotmerandaddress.entity.Customer;

import java.util.List;

public interface CustomerService {


    Customer saveCustomer(Customer customer);

    List<Customer> findAllCustomers();

    Customer findCustomerById(Long id);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Long id);
}
