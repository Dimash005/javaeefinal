package com.lab.BolatDinmukhamed.mapper;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedProfessorRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedProfessorResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedProfessor;
import org.springframework.stereotype.Component;

@Component
public class BolatDinmukhamedProfessorMapper {

    public BolatDinmukhamedProfessorResponse toResponse(BolatDinmukhamedProfessor entity) {
        BolatDinmukhamedProfessorResponse response = new BolatDinmukhamedProfessorResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setHireDate(entity.getHireDate());
        response.setSpecialization(entity.getSpecialization());
        response.setAcademicTitle(entity.getAcademicTitle());
        return response;
    }

    public BolatDinmukhamedProfessor toEntity(BolatDinmukhamedProfessorRequest request) {
        BolatDinmukhamedProfessor entity = new BolatDinmukhamedProfessor();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setHireDate(request.getHireDate());
        entity.setSpecialization(request.getSpecialization());
        entity.setAcademicTitle(request.getAcademicTitle());
        return entity;
    }

    public void updateEntity(BolatDinmukhamedProfessor entity, BolatDinmukhamedProfessorRequest request) {
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setHireDate(request.getHireDate());
        entity.setSpecialization(request.getSpecialization());
        entity.setAcademicTitle(request.getAcademicTitle());
    }
}
