package com.enigma.wmb.entity;

import com.enigma.wmb.constant.TableConstant;
import jakarta.persistence.*;

@Entity
@Table(name = TableConstant.TABLE_BILL_DETAIL)
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
    private Float price;
    @Column(name = "qty")
    private Integer qty;
}
