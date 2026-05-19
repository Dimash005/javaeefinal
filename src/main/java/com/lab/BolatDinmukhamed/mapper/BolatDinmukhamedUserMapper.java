package com.lab.BolatDinmukhamed.mapper;

import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedUserResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedUser;
import org.springframework.stereotype.Component;

@Component
public class BolatDinmukhamedUserMapper {

    public BolatDinmukhamedUserResponse toResponse(BolatDinmukhamedUser entity) {
        BolatDinmukhamedUserResponse response = new BolatDinmukhamedUserResponse();
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setEmail(entity.getEmail());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setStudentId(entity.getStudentId());
        response.setPhoneNumber(entity.getPhoneNumber());
        response.setAvatarUrl(entity.getAvatarUrl());
        response.setRole(entity.getRole());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }
}
