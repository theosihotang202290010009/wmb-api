package com.enigma.wmb.entity;

import com.enigma.wmb.constant.TableConstant;
import com.enigma.wmb.constant.TransTypeEnum;
import jakarta.persistence.*;
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
    @Enumerated(value = EnumType.STRING)
    private TransTypeEnum id;
    @Column(name = "description")
    private String description;
}
