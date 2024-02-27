package com.enigma.wmb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "menu_name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private Float price;
}
