package com.enigma.wmb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "mobile_phone")
    private String phone;
    @Column(name = "is_member")
    private Boolean member;
}
