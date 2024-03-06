package com.enigma.wmb.dto.request.bill;

import com.enigma.wmb.constant.TransTypeEnum;
import com.enigma.wmb.dto.request.bill_detail.BillDetailRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchBillRequest {
    private String id;
    private String customer;
    private String transDate;
    private String table;
    private TransTypeEnum transType;
    private List<BillDetailRequest> details;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}
