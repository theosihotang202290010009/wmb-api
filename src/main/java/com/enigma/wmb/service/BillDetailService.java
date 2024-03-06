package com.enigma.wmb.service;

import com.enigma.wmb.entity.BillDetail;

import java.util.List;

public interface BillDetailService {
    List<BillDetail> createBulk(List<BillDetail> billDetails);
}
