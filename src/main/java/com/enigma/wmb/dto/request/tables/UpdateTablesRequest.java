package com.enigma.wmb.dto.request.tables;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTablesRequest {
    private String id;
    private String name;
    private Boolean isEmpty;
}
