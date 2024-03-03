package com.enigma.wmb.dto.response;

import com.enigma.wmb.entity.UserCredential;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String id;
    private String customerName;
    private String customerPhone;
    private Boolean member;
    private UserCredential userCredential;
}
