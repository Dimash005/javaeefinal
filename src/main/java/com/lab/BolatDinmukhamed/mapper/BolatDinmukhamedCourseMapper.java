package com.lab.BolatDinmukhamed.mapper;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedCourseRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedCourseResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedCourse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BolatDinmukhamedCourseMapper {

    public BolatDinmukhamedCourseResponse toResponse(BolatDinmukhamedCourse entity) {
        BolatDinmukhamedCourseResponse response = new BolatDinmukhamedCourseResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setCourseCode(entity.getCourseCode());
        response.setDescription(entity.getDescription());
        response.setCredits(entity.getCredits());
        response.setMaxStudents(entity.getMaxStudents());
        response.setAvailableSeats(entity.getAvailableSeats());
        response.setSemester(entity.getSemester());
        response.setSyllabusUrl(entity.getSyllabusUrl());

        if (entity.getDepartment() != null) {
            response.setDepartmentName(entity.getDepartment().getName());
        }

        if (entity.getProfessors() != null) {
            response.setProfessorNames(
                    entity.getProfessors().stream()
                            .map(p -> p.getFirstName() + " " + p.getLastName())
                            .collect(Collectors.toSet())
            );
        }

        return response;
    }

    public BolatDinmukhamedCourse toEntity(BolatDinmukhamedCourseRequest request) {
        BolatDinmukhamedCourse entity = new BolatDinmukhamedCourse();
        entity.setTitle(request.getTitle());
        entity.setCourseCode(request.getCourseCode());
        entity.setDescription(request.getDescription());
        entity.setCredits(request.getCredits() != null ? request.getCredits() : 3);
        entity.setMaxStudents(request.getMaxStudents());
        entity.setAvailableSeats(request.getMaxStudents());
        entity.setSemester(request.getSemester());
        return entity;
    }

    public void updateEntity(BolatDinmukhamedCourse entity, BolatDinmukhamedCourseRequest request) {
        entity.setTitle(request.getTitle());
        entity.setCourseCode(request.getCourseCode());
        entity.setDescription(request.getDescription());
        if (request.getCredits() != null) {
            entity.setCredits(request.getCredits());
        }
        entity.setMaxStudents(request.getMaxStudents());
        entity.setSemester(request.getSemester());
    }
}
