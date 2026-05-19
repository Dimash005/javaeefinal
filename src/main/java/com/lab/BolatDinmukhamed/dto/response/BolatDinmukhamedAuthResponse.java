package com.lab.BolatDinmukhamed.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BolatDinmukhamedAuthResponse {
    private String token;
    private String username;
    private String role;
}
