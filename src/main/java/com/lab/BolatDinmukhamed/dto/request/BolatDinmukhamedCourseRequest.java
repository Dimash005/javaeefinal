package com.lab.BolatDinmukhamed.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class BolatDinmukhamedCourseRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 200)
    private String title;

    @Size(max = 20)
    private String courseCode;

    @Size(max = 2000)
    private String description;

    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 10)
    private Integer credits;

    @NotNull(message = "Max students is required")
    @Min(value = 1, message = "Max students must be at least 1")
    private Integer maxStudents;

    @Size(max = 50)
    private String semester;

    private Long departmentId;

    private Set<Long> professorIds;
}
