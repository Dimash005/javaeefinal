package com.lab.BolatDinmukhamed.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BolatDinmukhamedGradeResponse {
    private Long id;
    private Long userId;
    private String username;
    private Long courseId;
    private String courseTitle;
    private Double score;
    private String letterGrade;
    private String feedback;
    private LocalDateTime createdAt;
}
