package com.enigma.wmb.dto.request.Menu;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchMenuRequest {
    private String id;
    private String name;
    private Long price;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}
