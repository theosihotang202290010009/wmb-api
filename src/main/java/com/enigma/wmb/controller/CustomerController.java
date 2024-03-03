package com.enigma.wmb.controller;

import com.enigma.wmb.constant.APIUrl;
import com.enigma.wmb.dto.request.customer.NewCustomerRequest;
import com.enigma.wmb.dto.request.customer.SearchCustomerRequest;
import com.enigma.wmb.dto.request.customer.UpdateCustomerRequest;
import com.enigma.wmb.dto.response.CommonResponse;
import com.enigma.wmb.dto.response.CustomerResponse;
import com.enigma.wmb.dto.response.PagingResponse;
import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = APIUrl.API_CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAll(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "member", required = false) Boolean member,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        SearchCustomerRequest request = SearchCustomerRequest.builder()
                .name(name)
                .phone(phone)
                .member(member)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<CustomerResponse> getAll = customerService.getAll(request);
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(getAll.getTotalPages())
                .totalElement(getAll.getTotalElements())
                .page(getAll.getPageable().getPageNumber() + 1)
                .size(getAll.getPageable().getPageSize())
                .hasNext(getAll.hasNext())
                .hasPrevious(getAll.hasPrevious())
                .build();
        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully get all data customer")
                .paging(pagingResponse)
                .data(getAll.getContent())
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> findById(@PathVariable String id) {
        CustomerResponse customer = customerService.getCustomerById(id);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully get data with ID " + id)
                .data(customer)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<CustomerResponse>> update(@RequestBody UpdateCustomerRequest request) {
        CustomerResponse update = customerService.update(request);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully update data with ID " + request.getId())
                .data(update)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> deleteCustomer(@PathVariable String id) {
        customerService.delete(id);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully delete data with ID " + id)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
