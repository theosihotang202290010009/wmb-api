package com.enigma.wmb.entity;

import com.enigma.wmb.constant.TableConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = TableConstant.TABLE_TRANS_TYPE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransType {
    @Id
    private String id;
    @Column(name = "description")
    private String description;
}
