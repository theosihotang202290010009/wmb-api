package com.enigma.wmb.service.impl;

import com.enigma.wmb.dto.request.bill.NewBillRequest;
import com.enigma.wmb.dto.request.bill.SearchBillRequest;
import com.enigma.wmb.dto.response.BillResponse;
import com.enigma.wmb.dto.response.CustomerResponse;
import com.enigma.wmb.dto.response.MenuResponse;
import com.enigma.wmb.dto.response.TablesResponse;
import com.enigma.wmb.entity.*;
import com.enigma.wmb.repository.BillRepository;
import com.enigma.wmb.service.*;
import com.enigma.wmb.specification.BillSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final CustomerService customerService;
    private final TablesService tablesService;
    private final TransTypeService transTypeService;
    private final MenuService menuService;
    private final BillDetailService billDetailService;

    @Override
    public BillResponse create(NewBillRequest request) {
        CustomerResponse customerById = customerService.getCustomerById(request.getCustomerId());
        TablesResponse tablesResponse = tablesService.getById(request.getTable());
        TransType transTypeResponse = transTypeService.findById(request.getTransType().name());
        Customer customer = Customer.builder()
                .id(customerById.getId())
                .name(customerById.getCustomerName())
                .phone(customerById.getCustomerPhone())
                .member(customerById.getMember())
                .userCredential(customerById.getUserCredential())
                .build();

        Tables table = Tables.builder()
                .id(tablesResponse.getId())
                .name(tablesResponse.getName())
                .isEmpty(tablesResponse.getIsEmpty())
                .build();

        TransType transType = TransType.builder()
                .id(transTypeResponse.getId())
                .description(transTypeResponse.getDescription())
                .build();

        Bill bill = Bill.builder()
                .transDate(new Date())
                .customerId(customer)
                .tableId(table)
                .transTypeId(transType)
                .build();
        billRepository.saveAndFlush(bill);

        List<BillDetail> billDetails = request.getDetails().stream()
                .map(detail -> {
                    MenuResponse menuResponse = menuService.findByIdMenu(detail.getMenu());
                    Menu menu = Menu.builder()
                            .id(menuResponse.getId())
                            .name(menuResponse.getName())
                            .price(menuResponse.getPrice())
                            .build();
                    return BillDetail.builder()
                            .billId(bill)
                            .menuId(menu)
                            .qty(detail.getQty())
                            .price(detail.getPrice())
                            .build();
                }).toList();
        billDetailService.createBulk(billDetails);
        bill.setBillDetails(billDetails);

        return BillResponse.builder()
                .customerId(bill.getCustomerId().getId())
                .transDate(bill.getTransDate())
                .tableId(bill.getTableId().getId())
                .transTypeId(bill.getTransTypeId().getId())
                .build();
    }

    @Override
    public Page<BillResponse> getAll(SearchBillRequest request) {
        Specification<Bill> specification = BillSpecification.getSpecification(request);
        if (request.getPage() <= 0) request.setPage(1);
        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), request.getSortBy());
        Pageable pageable = PageRequest.of((request.getPage() - 1), request.getSize(), sort);
        Page<Bill> getAll = billRepository.findAll(specification, pageable);
        return getAll.map(response ->
                BillResponse.builder()
                        .id(response.getId())
                        .customerId(response.getCustomerId().getId())
                        .customerName(response.getCustomerId().getName())
                        .tableId(response.getTableId().getId())
                        .transTypeId(response.getTransTypeId().getId())
                        .transDate(response.getTransDate())
                        .build()
        );
    }

    @Override
    public BillResponse findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
