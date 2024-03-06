package com.enigma.wmb.dto.response;

import com.enigma.wmb.constant.TransTypeEnum;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillResponse {
    private String id;
    private String customerId;
    private String customerName;
    private String tableId;
    private TransTypeEnum transTypeId;
    private Date transDate;
}
