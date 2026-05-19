package com.lab.BolatDinmukhamed.service;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedLoginRequest;
import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedUserRegisterRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedAuthResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedUser;
import com.lab.BolatDinmukhamed.entity.enums.BolatDinmukhamedRole;
import com.lab.BolatDinmukhamed.exception.BolatDinmukhamedBadRequestException;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedUserRepository;
import com.lab.BolatDinmukhamed.security.BolatDinmukhamedJwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BolatDinmukhamedAuthService {

    private final BolatDinmukhamedUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final BolatDinmukhamedJwtUtil jwtUtil;
    private final BolatDinmukhamedNotificationService notificationService;

    public BolatDinmukhamedAuthResponse register(BolatDinmukhamedUserRegisterRequest request) {
        log.info("Registering user: {}", request.getUsername());

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BolatDinmukhamedBadRequestException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BolatDinmukhamedBadRequestException("Email already exists");
        }

        BolatDinmukhamedUser user = BolatDinmukhamedUser.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .studentId(request.getStudentId())
                .phoneNumber(request.getPhoneNumber())
                .role(BolatDinmukhamedRole.ROLE_STUDENT)
                .enabled(true)
                .build();

        userRepository.save(user);
        log.info("User registered successfully: {}", user.getUsername());

        notificationService.sendWelcomeEmail(user.getEmail(), user.getUsername());

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole().name())
                .build();

        String token = jwtUtil.generateToken(userDetails);
        return new BolatDinmukhamedAuthResponse(token, user.getUsername(), user.getRole().name());
    }

    public BolatDinmukhamedAuthResponse login(BolatDinmukhamedLoginRequest request) {
        log.info("Login attempt: {}", request.getUsername());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        BolatDinmukhamedUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BolatDinmukhamedBadRequestException("User not found"));

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole().name())
                .build();

        String token = jwtUtil.generateToken(userDetails);
        log.info("User logged in: {}", user.getUsername());
        return new BolatDinmukhamedAuthResponse(token, user.getUsername(), user.getRole().name());
    }
}