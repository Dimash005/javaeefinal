package com.lab.BolatDinmukhamed.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class BolatDinmukhamedCourseResponse {
    private Long id;
    private String title;
    private String courseCode;
    private String description;
    private Integer credits;
    private Integer maxStudents;
    private Integer availableSeats;
    private String semester;
    private String syllabusUrl;
    private String departmentName;
    private Set<String> professorNames;
}
