package com.enigma.wmb.service.impl;

import com.enigma.wmb.dto.request.customer.SearchCustomerRequest;
import com.enigma.wmb.dto.request.customer.UpdateCustomerRequest;
import com.enigma.wmb.dto.response.CustomerResponse;
import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.entity.UserCredential;
import com.enigma.wmb.repository.CustomerRepository;
import com.enigma.wmb.service.CustomerService;
import com.enigma.wmb.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public CustomerResponse create(Customer customer) {
        Customer createCustomer = customerRepository.saveAndFlush(customer);
        return CustomerResponse.builder()
                .id(createCustomer.getId())
                .customerName(createCustomer.getName())
                .build();
    }

    @Override
    public Page<CustomerResponse> getAll(SearchCustomerRequest request) {
        Specification<Customer> specification = CustomerSpecification.getSpecification(request);
        if (request.getPage()<=0) request.setPage(1);
        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), request.getSortBy());
        Pageable pageable = PageRequest.of((request.getPage()-1), request.getSize(),sort);
        Page<Customer> getAll = customerRepository.findAll(specification, pageable);
        return getAll.map(customer -> CustomerResponse.builder()
                .id(customer.getId())
                .customerName(customer.getName())
                .member(customer.getMember())
                .customerPhone(customer.getPhone())
                .build());
    }

    @Override
    public CustomerResponse getCustomerById(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find data with ID " + id));
        return CustomerResponse.builder()
                .id(customer.getId())
                .customerName(customer.getName())
                .customerPhone(customer.getPhone())
                .member(customer.getMember())
                .build();
    }

    @Override
    public CustomerResponse update(UpdateCustomerRequest request) {
//        CustomerResponse customerById = getCustomerById(request.getId());
        Customer customer = customerRepository.findById(request.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found"));
        customer.setId(request.getId());
        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setMember(customer.getMember());
        customer.setUserCredential(customer.getUserCredential());
//        Customer updateCustomer = Customer.builder()
//                .id(customerById.getId())
//                .name(customerById.getCustomerName())
//                .phone(customerById.getCustomerPhone())
//                .member(customerById.getMember())
//                .userCredential(customerById.getUserCredential())
//                .build();
        Customer updateCustomer = customerRepository.saveAndFlush(customer);
        return CustomerResponse.builder()
                .id(updateCustomer.getId())
                .customerName(updateCustomer.getName())
                .customerPhone(updateCustomer.getPhone())
                .member(updateCustomer.getMember())
                .build();
    }

    @Override
    public void delete(String id) {
        CustomerResponse customerById = getCustomerById(id);
        Customer delete = Customer.builder()
                .id(customerById.getId())
                .name(customerById.getCustomerName())
                .phone(customerById.getCustomerPhone())
                .build();
        customerRepository.delete(delete);
    }


}
