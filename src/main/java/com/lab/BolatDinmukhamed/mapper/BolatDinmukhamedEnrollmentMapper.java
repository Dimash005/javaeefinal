package com.lab.BolatDinmukhamed.mapper;

import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedEnrollmentResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedEnrollment;
import org.springframework.stereotype.Component;

@Component
public class BolatDinmukhamedEnrollmentMapper {

    public BolatDinmukhamedEnrollmentResponse toResponse(BolatDinmukhamedEnrollment entity) {
        BolatDinmukhamedEnrollmentResponse response = new BolatDinmukhamedEnrollmentResponse();
        response.setId(entity.getId());
        response.setUserId(entity.getUser().getId());
        response.setUsername(entity.getUser().getUsername());
        response.setCourseId(entity.getCourse().getId());
        response.setCourseTitle(entity.getCourse().getTitle());
        response.setEnrollmentDate(entity.getEnrollmentDate());
        response.setCompletionDate(entity.getCompletionDate());
        response.setStatus(entity.getStatus());
        response.setNotes(entity.getNotes());
        return response;
    }
}
