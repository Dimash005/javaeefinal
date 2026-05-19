package com.lab.BolatDinmukhamed.dto.response;

import com.lab.BolatDinmukhamed.entity.enums.BolatDinmukhamedEnrollmentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BolatDinmukhamedEnrollmentResponse {
    private Long id;
    private Long userId;
    private String username;
    private Long courseId;
    private String courseTitle;
    private LocalDate enrollmentDate;
    private LocalDate completionDate;
    private BolatDinmukhamedEnrollmentStatus status;
    private String notes;
}
