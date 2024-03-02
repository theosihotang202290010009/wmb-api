package com.enigma.wmb.entity;

import com.enigma.wmb.constant.TableConstant;
import com.enigma.wmb.constant.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = TableConstant.TABLE_ROLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
}
