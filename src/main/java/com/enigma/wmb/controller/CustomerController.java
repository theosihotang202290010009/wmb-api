package com.enigma.wmb.controller;

import com.enigma.wmb.constant.APIUrl;
import com.enigma.wmb.dto.request.CustomerRequest;
import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = APIUrl.API_CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public Customer create(@RequestBody Customer customer){
        return  customerService.create(customer);
    }

    @GetMapping
    public List<Customer> getAll(CustomerRequest request){
        return customerService.getAll(request);
    }
}
