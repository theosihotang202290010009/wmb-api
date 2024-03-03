package com.enigma.wmb.service.impl;

import com.enigma.wmb.constant.TransTypeEnum;
import com.enigma.wmb.entity.TransType;
import com.enigma.wmb.repository.TransTypeRepository;
import com.enigma.wmb.service.TransTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransTypeServiceImpl implements TransTypeService {
    private final TransTypeRepository transTypeRepository;

    @Override
    public TransType findById(String id) {
        return transTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find data with ID " + id));
    }

    @Override
    public List<TransType> getAll() {
        return transTypeRepository.findAll();
    }
}
