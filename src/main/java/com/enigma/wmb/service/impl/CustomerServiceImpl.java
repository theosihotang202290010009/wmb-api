package com.enigma.wmb.service.impl;

import com.enigma.wmb.dto.request.CustomerRequest;
import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.repository.CustomerRepository;
import com.enigma.wmb.service.CustomerService;
import com.enigma.wmb.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public Customer create(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public List<Customer> getAll(CustomerRequest request) {
        Specification<Customer> specification = CustomerSpecification.getSpecification(request);
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String id) {
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Customer findById(String id) {
        return null;
    }
}
