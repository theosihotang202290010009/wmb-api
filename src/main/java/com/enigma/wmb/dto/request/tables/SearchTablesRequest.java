package com.enigma.wmb.dto.request.tables;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchTablesRequest {
    private String id;
    private String name;
    private Boolean isEmpty;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}
