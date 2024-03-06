package com.enigma.wmb.service;

import com.enigma.wmb.dto.request.bill.NewBillRequest;
import com.enigma.wmb.dto.request.bill.SearchBillRequest;
import com.enigma.wmb.dto.response.BillResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillService {
    BillResponse create(NewBillRequest request);
    Page<BillResponse> getAll(SearchBillRequest request);
    BillResponse findById(String id);
    void delete(String id);
}
