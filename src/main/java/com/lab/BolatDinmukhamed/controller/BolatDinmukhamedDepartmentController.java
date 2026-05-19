package com.lab.BolatDinmukhamed.controller;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedDepartmentRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedDepartmentResponse;
import com.lab.BolatDinmukhamed.service.BolatDinmukhamedDepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class BolatDinmukhamedDepartmentController {

    private final BolatDinmukhamedDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<BolatDinmukhamedDepartmentResponse>> getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BolatDinmukhamedDepartmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BolatDinmukhamedDepartmentResponse> create(
            @Valid @RequestBody BolatDinmukhamedDepartmentRequest request) {
        return new ResponseEntity<>(departmentService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BolatDinmukhamedDepartmentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BolatDinmukhamedDepartmentRequest request) {
        return ResponseEntity.ok(departmentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}