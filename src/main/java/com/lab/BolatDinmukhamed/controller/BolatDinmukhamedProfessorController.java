package com.lab.BolatDinmukhamed.controller;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedProfessorRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedProfessorResponse;
import com.lab.BolatDinmukhamed.service.BolatDinmukhamedProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
@RequiredArgsConstructor
public class BolatDinmukhamedProfessorController {

    private final BolatDinmukhamedProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<BolatDinmukhamedProfessorResponse>> getAll() {
        return ResponseEntity.ok(professorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BolatDinmukhamedProfessorResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BolatDinmukhamedProfessorResponse> create(
            @Valid @RequestBody BolatDinmukhamedProfessorRequest request) {
        return new ResponseEntity<>(professorService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BolatDinmukhamedProfessorResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BolatDinmukhamedProfessorRequest request) {
        return ResponseEntity.ok(professorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}