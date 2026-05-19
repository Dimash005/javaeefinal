package com.lab.BolatDinmukhamed.controller;

import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedUserResponse;
import com.lab.BolatDinmukhamed.service.BolatDinmukhamedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class BolatDinmukhamedUserController {

    private final BolatDinmukhamedUserService userService;

    @GetMapping
    public ResponseEntity<List<BolatDinmukhamedUserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BolatDinmukhamedUserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}