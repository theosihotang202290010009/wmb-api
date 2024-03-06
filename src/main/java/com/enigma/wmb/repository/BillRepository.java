package com.enigma.wmb.repository;

import com.enigma.wmb.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BillRepository extends JpaRepository<Bill, String>, JpaSpecificationExecutor<Bill> {
}
