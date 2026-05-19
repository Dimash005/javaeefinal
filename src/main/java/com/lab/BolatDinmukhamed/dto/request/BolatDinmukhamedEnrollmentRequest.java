package com.lab.BolatDinmukhamed.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BolatDinmukhamedEnrollmentRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Course ID is required")
    private Long courseId;
}
