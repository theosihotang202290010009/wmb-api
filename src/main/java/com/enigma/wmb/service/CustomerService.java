package com.enigma.wmb.service;

import com.enigma.wmb.dto.request.CustomerRequest;
import com.enigma.wmb.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    List<Customer> getAll(CustomerRequest request);
    Customer getCustomerById(String id);
    Customer update(Customer customer);
    void delete(String id);
    Customer findById(String id);
}
