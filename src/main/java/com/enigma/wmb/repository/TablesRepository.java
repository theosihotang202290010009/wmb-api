package com.enigma.wmb.repository;

import com.enigma.wmb.entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablesRepository extends JpaRepository<Tables, String> {
}
