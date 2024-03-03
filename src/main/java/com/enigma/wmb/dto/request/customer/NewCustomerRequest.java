package com.enigma.wmb.dto.request.customer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCustomerRequest {
    private String name;
    private String phone;
    private Boolean member;
}
