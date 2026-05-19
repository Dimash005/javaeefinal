package com.lab.BolatDinmukhamed.controller;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedCourseRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedCourseResponse;
import com.lab.BolatDinmukhamed.service.BolatDinmukhamedCourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class BolatDinmukhamedCourseController {

    private final BolatDinmukhamedCourseService courseService;

    @GetMapping
    public ResponseEntity<Page<BolatDinmukhamedCourseResponse>> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long departmentId,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(courseService.getAll(search, departmentId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BolatDinmukhamedCourseResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BolatDinmukhamedCourseResponse> create(
            @Valid @RequestBody BolatDinmukhamedCourseRequest request) {
        return new ResponseEntity<>(courseService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BolatDinmukhamedCourseResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BolatDinmukhamedCourseRequest request) {
        return ResponseEntity.ok(courseService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}