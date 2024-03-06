package com.enigma.wmb.dto.request.bill_detail;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDetailRequest {
    private String menu;
    private Integer qty;
    private Long price;
}
