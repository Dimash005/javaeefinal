package com.lab.BolatDinmukhamed.service;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedGradeRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedGradeResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedCourse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedGrade;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedUser;
import com.lab.BolatDinmukhamed.exception.BolatDinmukhamedResourceNotFoundException;
import com.lab.BolatDinmukhamed.mapper.BolatDinmukhamedGradeMapper;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedCourseRepository;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedGradeRepository;
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
public class BolatDinmukhamedGradeService {

    private final BolatDinmukhamedGradeRepository gradeRepository;
    private final BolatDinmukhamedUserRepository userRepository;
    private final BolatDinmukhamedCourseRepository courseRepository;
    private final BolatDinmukhamedGradeMapper gradeMapper;

    public List<BolatDinmukhamedGradeResponse> getAll() {
        log.info("Fetching all grades");
        return gradeRepository.findAll().stream()
                .map(gradeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<BolatDinmukhamedGradeResponse> getByCourseId(Long courseId) {
        return gradeRepository.findByCourseId(courseId).stream()
                .map(gradeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<BolatDinmukhamedGradeResponse> getByUserId(Long userId) {
        return gradeRepository.findByUserId(userId).stream()
                .map(gradeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BolatDinmukhamedGradeResponse create(BolatDinmukhamedGradeRequest request) {
        log.info("Creating grade: userId={}, courseId={}", request.getUserId(), request.getCourseId());

        BolatDinmukhamedUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                        "User with id " + request.getUserId() + " not found"));

        BolatDinmukhamedCourse course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                        "Course with id " + request.getCourseId() + " not found"));

        BolatDinmukhamedGrade grade = BolatDinmukhamedGrade.builder()
                .user(user)
                .course(course)
                .score(request.getScore())
                .letterGrade(request.getLetterGrade())
                .feedback(request.getFeedback())
                .build();

        return gradeMapper.toResponse(gradeRepository.save(grade));
    }

    public void delete(Long id) {
        log.info("Deleting grade {}", id);
        BolatDinmukhamedGrade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                        "Grade with id " + id + " not found"));
        gradeRepository.delete(grade);
    }
}