package com.enigma.wmb.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    private String name;
    private String phone;
    private Boolean member;
}
