package com.enigma.wmb.dto.request.tables;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTablesRequest {
    private String name;
    private Boolean isEmpty;
}
