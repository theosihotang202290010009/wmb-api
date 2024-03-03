package com.enigma.wmb.dto.request.menu;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMenuRequest {
    private String id;
    private String name;
    private Long price;
}
