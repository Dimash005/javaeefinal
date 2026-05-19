package com.lab.BolatDinmukhamed.service;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedDepartmentRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedDepartmentResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedDepartment;
import com.lab.BolatDinmukhamed.exception.BolatDinmukhamedResourceNotFoundException;
import com.lab.BolatDinmukhamed.mapper.BolatDinmukhamedDepartmentMapper;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedDepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BolatDinmukhamedDepartmentService {

    private final BolatDinmukhamedDepartmentRepository departmentRepository;
    private final BolatDinmukhamedDepartmentMapper departmentMapper;

    public List<BolatDinmukhamedDepartmentResponse> getAll() {
        log.info("Fetching all departments");
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BolatDinmukhamedDepartmentResponse getById(Long id) {
        log.info("Fetching department with id {}", id);
        return departmentMapper.toResponse(findDepartmentById(id));
    }

    public BolatDinmukhamedDepartmentResponse create(BolatDinmukhamedDepartmentRequest request) {
        log.info("Creating department: {}", request.getName());
        BolatDinmukhamedDepartment department = departmentMapper.toEntity(request);
        BolatDinmukhamedDepartment saved = departmentRepository.save(department);
        return departmentMapper.toResponse(saved);
    }

    public BolatDinmukhamedDepartmentResponse update(Long id, BolatDinmukhamedDepartmentRequest request) {
        log.info("Updating department with id {}", id);
        BolatDinmukhamedDepartment department = findDepartmentById(id);
        departmentMapper.updateEntity(department, request);
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    public void delete(Long id) {
        log.info("Deleting department with id {}", id);
        departmentRepository.delete(findDepartmentById(id));
    }

    public BolatDinmukhamedDepartment findDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                        "Department with id " + id + " not found"));
    }
}