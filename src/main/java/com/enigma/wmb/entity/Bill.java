package com.enigma.wmb.entity;

import com.enigma.wmb.constant.TableConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = TableConstant.TABLE_BILL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "trans_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta")
    private Date transDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private Tables tableId;
    @ManyToOne
    @JoinColumn(name = "trans_type")
    private TransType transTypeId;
    @OneToMany(mappedBy = "billId")
    private List<BillDetail> billDetails;
}
