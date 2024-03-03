package com.enigma.wmb.dto.request.menu;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewMenuRequest {
    @NotBlank(message = "Menu name is required")
    private String name;
    private Long price;
}
