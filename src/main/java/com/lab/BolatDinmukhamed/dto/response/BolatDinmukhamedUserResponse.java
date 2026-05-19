package com.lab.BolatDinmukhamed.dto.response;

import com.lab.BolatDinmukhamed.entity.enums.BolatDinmukhamedRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BolatDinmukhamedUserResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String studentId;
    private String phoneNumber;
    private String avatarUrl;
    private BolatDinmukhamedRole role;
    private LocalDateTime createdAt;
}
