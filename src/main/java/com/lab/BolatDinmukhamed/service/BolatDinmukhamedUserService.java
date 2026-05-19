package com.lab.BolatDinmukhamed.service;

import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedUserResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedUser;
import com.lab.BolatDinmukhamed.exception.BolatDinmukhamedResourceNotFoundException;
import com.lab.BolatDinmukhamed.mapper.BolatDinmukhamedUserMapper;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedUserRepository;
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
public class BolatDinmukhamedUserService {

    private final BolatDinmukhamedUserRepository userRepository;
    private final BolatDinmukhamedUserMapper userMapper;

    public List<BolatDinmukhamedUserResponse> getAll() {
        log.info("Fetching all users");
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BolatDinmukhamedUserResponse getById(Long id) {
        log.info("Fetching user with id {}", id);
        return userMapper.toResponse(findUserById(id));
    }

    public void delete(Long id) {
        log.info("Deleting user with id {}", id);
        userRepository.delete(findUserById(id));
    }

    public BolatDinmukhamedUser findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                        "User with id " + id + " not found"));
    }
}