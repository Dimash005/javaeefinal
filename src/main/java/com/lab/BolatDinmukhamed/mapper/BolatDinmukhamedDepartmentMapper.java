package com.lab.BolatDinmukhamed.mapper;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedDepartmentRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedDepartmentResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedDepartment;
import org.springframework.stereotype.Component;

@Component
public class BolatDinmukhamedDepartmentMapper {

    public BolatDinmukhamedDepartmentResponse toResponse(BolatDinmukhamedDepartment entity) {
        BolatDinmukhamedDepartmentResponse response = new BolatDinmukhamedDepartmentResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setHeadOfDepartment(entity.getHeadOfDepartment());
        return response;
    }

    public BolatDinmukhamedDepartment toEntity(BolatDinmukhamedDepartmentRequest request) {
        BolatDinmukhamedDepartment entity = new BolatDinmukhamedDepartment();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setHeadOfDepartment(request.getHeadOfDepartment());
        return entity;
    }

    public void updateEntity(BolatDinmukhamedDepartment entity, BolatDinmukhamedDepartmentRequest request) {
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setHeadOfDepartment(request.getHeadOfDepartment());
    }
}
