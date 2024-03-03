package com.enigma.wmb.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TablesResponse {
    private String name;
    private Boolean isEmpty;
}
