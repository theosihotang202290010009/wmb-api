package com.enigma.wmb.entity;

import com.enigma.wmb.constant.TableConstant;
import jakarta.persistence.*;

@Entity
@Table(name = TableConstant.TABLE_TABLES)
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "table_name")
    private String name;

}
