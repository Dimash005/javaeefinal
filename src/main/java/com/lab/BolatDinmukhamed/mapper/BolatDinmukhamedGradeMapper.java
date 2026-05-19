package com.lab.BolatDinmukhamed.mapper;

import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedGradeResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedGrade;
import org.springframework.stereotype.Component;

@Component
public class BolatDinmukhamedGradeMapper {

    public BolatDinmukhamedGradeResponse toResponse(BolatDinmukhamedGrade entity) {
        BolatDinmukhamedGradeResponse response = new BolatDinmukhamedGradeResponse();
        response.setId(entity.getId());
        response.setUserId(entity.getUser().getId());
        response.setUsername(entity.getUser().getUsername());
        response.setCourseId(entity.getCourse().getId());
        response.setCourseTitle(entity.getCourse().getTitle());
        response.setScore(entity.getScore());
        response.setLetterGrade(entity.getLetterGrade());
        response.setFeedback(entity.getFeedback());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }
}
