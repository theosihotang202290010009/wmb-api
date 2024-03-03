package com.enigma.wmb.service;

import com.enigma.wmb.entity.TransType;

import java.util.List;

public interface TransTypeService {
    List<TransType> getAll();
    TransType findById(String id);
}
