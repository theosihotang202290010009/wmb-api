package com.enigma.wmb.repository;

import com.enigma.wmb.constant.TransTypeEnum;
import com.enigma.wmb.entity.TransType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransTypeRepository extends JpaRepository<TransType, String> {
}
