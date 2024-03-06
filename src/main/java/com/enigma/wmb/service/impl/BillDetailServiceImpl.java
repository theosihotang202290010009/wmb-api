package com.enigma.wmb.service.impl;

import com.enigma.wmb.dto.request.bill_detail.BillDetailRequest;
import com.enigma.wmb.entity.BillDetail;
import com.enigma.wmb.repository.BillDetailRepository;
import com.enigma.wmb.service.BillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements BillDetailService {
    private final BillDetailRepository billDetailRepository;
    @Override
    public List<BillDetail> createBulk(List<BillDetail> billDetails) {
        return billDetailRepository.saveAllAndFlush(billDetails);
    }
}
