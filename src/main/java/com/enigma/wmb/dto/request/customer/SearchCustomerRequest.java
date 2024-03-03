package com.enigma.wmb.dto.request.customer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCustomerRequest {
    private String name;
    private String phone;
    private Boolean member;
    private String userCredentialId;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}
