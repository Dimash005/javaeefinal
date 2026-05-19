package com.lab.BolatDinmukhamed.service;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedProfessorRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedProfessorResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedProfessor;
import com.lab.BolatDinmukhamed.exception.BolatDinmukhamedResourceNotFoundException;
import com.lab.BolatDinmukhamed.mapper.BolatDinmukhamedProfessorMapper;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedProfessorRepository;
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
public class BolatDinmukhamedProfessorService {

    private final BolatDinmukhamedProfessorRepository professorRepository;
    private final BolatDinmukhamedProfessorMapper professorMapper;

    public List<BolatDinmukhamedProfessorResponse> getAll() {
        log.info("Fetching all professors");
        return professorRepository.findAll().stream()
                .map(professorMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BolatDinmukhamedProfessorResponse getById(Long id) {
        log.info("Fetching professor with id {}", id);
        return professorMapper.toResponse(findProfessorById(id));
    }

    public BolatDinmukhamedProfessorResponse create(BolatDinmukhamedProfessorRequest request) {
        log.info("Creating professor: {} {}", request.getFirstName(), request.getLastName());
        BolatDinmukhamedProfessor professor = professorMapper.toEntity(request);
        return professorMapper.toResponse(professorRepository.save(professor));
    }

    public BolatDinmukhamedProfessorResponse update(Long id, BolatDinmukhamedProfessorRequest request) {
        log.info("Updating professor with id {}", id);
        BolatDinmukhamedProfessor professor = findProfessorById(id);
        professorMapper.updateEntity(professor, request);
        return professorMapper.toResponse(professorRepository.save(professor));
    }

    public void delete(Long id) {
        log.info("Deleting professor with id {}", id);
        professorRepository.delete(findProfessorById(id));
    }

    public BolatDinmukhamedProfessor findProfessorById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                        "Professor with id " + id + " not found"));
    }
}