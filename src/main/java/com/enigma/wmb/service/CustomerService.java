package com.enigma.wmb.service;

import com.enigma.wmb.dto.request.customer.NewCustomerRequest;
import com.enigma.wmb.dto.request.customer.SearchCustomerRequest;
import com.enigma.wmb.dto.request.customer.UpdateCustomerRequest;
import com.enigma.wmb.dto.response.CustomerResponse;
import com.enigma.wmb.entity.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    CustomerResponse create(Customer customer);
    Page<CustomerResponse> getAll(SearchCustomerRequest request);
    CustomerResponse getCustomerById(String id);
    CustomerResponse update(UpdateCustomerRequest customer);
    void delete(String id);
}
