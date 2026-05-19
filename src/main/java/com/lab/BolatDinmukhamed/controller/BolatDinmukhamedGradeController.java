package com.lab.BolatDinmukhamed.controller;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedGradeRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedGradeResponse;
import com.lab.BolatDinmukhamed.service.BolatDinmukhamedGradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class BolatDinmukhamedGradeController {

    private final BolatDinmukhamedGradeService gradeService;

    @GetMapping
    public ResponseEntity<List<BolatDinmukhamedGradeResponse>> getAll(
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) Long userId) {
        if (courseId != null) {
            return ResponseEntity.ok(gradeService.getByCourseId(courseId));
        }
        if (userId != null) {
            return ResponseEntity.ok(gradeService.getByUserId(userId));
        }
        return ResponseEntity.ok(gradeService.getAll());
    }

    @PostMapping
    public ResponseEntity<BolatDinmukhamedGradeResponse> create(
            @Valid @RequestBody BolatDinmukhamedGradeRequest request) {
        return new ResponseEntity<>(gradeService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gradeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}