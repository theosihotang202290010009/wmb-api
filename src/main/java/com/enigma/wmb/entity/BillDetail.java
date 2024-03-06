package com.enigma.wmb.entity;

import com.enigma.wmb.constant.TableConstant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = TableConstant.TABLE_BILL_DETAIL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill billId;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menuId;
    private Long price;
    @Column(name = "qty")
    private Integer qty;
}
