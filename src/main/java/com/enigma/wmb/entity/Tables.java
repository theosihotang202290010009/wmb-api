package com.enigma.wmb.entity;

import com.enigma.wmb.constant.TableConstant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = TableConstant.TABLE_TABLES)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "table_name")
    private String name;
    @Column(name = "isEmpty")
    private Boolean isEmpty;

}
