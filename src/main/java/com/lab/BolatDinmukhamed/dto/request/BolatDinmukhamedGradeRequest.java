package com.lab.BolatDinmukhamed.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BolatDinmukhamedGradeRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotNull(message = "Score is required")
    @DecimalMin(value = "0.0", message = "Score must be at least 0")
    @DecimalMax(value = "100.0", message = "Score must be at most 100")
    private Double score;

    @Size(max = 2)
    private String letterGrade;

    @Size(max = 1000)
    private String feedback;
}
