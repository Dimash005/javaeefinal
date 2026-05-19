package com.lab.BolatDinmukhamed.controller;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedEnrollmentRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedEnrollmentResponse;
import com.lab.BolatDinmukhamed.service.BolatDinmukhamedEnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class BolatDinmukhamedEnrollmentController {

    private final BolatDinmukhamedEnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<BolatDinmukhamedEnrollmentResponse>> getAll(
            @RequestParam(required = false) Long userId) {
        if (userId != null) {
            return ResponseEntity.ok(enrollmentService.getByUserId(userId));
        }
        return ResponseEntity.ok(enrollmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BolatDinmukhamedEnrollmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BolatDinmukhamedEnrollmentResponse> enroll(
            @Valid @RequestBody BolatDinmukhamedEnrollmentRequest request) {
        return new ResponseEntity<>(enrollmentService.enroll(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<BolatDinmukhamedEnrollmentResponse> complete(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.complete(id));
    }

    @PutMapping("/{id}/drop")
    public ResponseEntity<BolatDinmukhamedEnrollmentResponse> drop(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.drop(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}