package com.lab.BolatDinmukhamed.controller;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedLoginRequest;
import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedUserRegisterRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedAuthResponse;
import com.lab.BolatDinmukhamed.service.BolatDinmukhamedAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class BolatDinmukhamedAuthController {

    private final BolatDinmukhamedAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<BolatDinmukhamedAuthResponse> register(
            @Valid @RequestBody BolatDinmukhamedUserRegisterRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<BolatDinmukhamedAuthResponse> login(
            @Valid @RequestBody BolatDinmukhamedLoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}