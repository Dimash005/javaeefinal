package com.lab.BolatDinmukhamed.service;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedEnrollmentRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedEnrollmentResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedCourse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedEnrollment;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedUser;
import com.lab.BolatDinmukhamed.entity.enums.BolatDinmukhamedEnrollmentStatus;
import com.lab.BolatDinmukhamed.exception.BolatDinmukhamedBadRequestException;
import com.lab.BolatDinmukhamed.exception.BolatDinmukhamedResourceNotFoundException;
import com.lab.BolatDinmukhamed.mapper.BolatDinmukhamedEnrollmentMapper;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedCourseRepository;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedEnrollmentRepository;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BolatDinmukhamedEnrollmentService {

    private final BolatDinmukhamedEnrollmentRepository enrollmentRepository;
    private final BolatDinmukhamedUserRepository userRepository;
    private final BolatDinmukhamedCourseRepository courseRepository;
    private final BolatDinmukhamedEnrollmentMapper enrollmentMapper;

    public List<BolatDinmukhamedEnrollmentResponse> getAll() {
        log.info("Fetching all enrollments");
        return enrollmentRepository.findAll().stream()
                .map(enrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<BolatDinmukhamedEnrollmentResponse> getByUserId(Long userId) {
        log.info("Fetching enrollments for user {}", userId);
        return enrollmentRepository.findByUserId(userId).stream()
                .map(enrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BolatDinmukhamedEnrollmentResponse getById(Long id) {
        return enrollmentMapper.toResponse(findEnrollmentById(id));
    }

    public BolatDinmukhamedEnrollmentResponse enroll(BolatDinmukhamedEnrollmentRequest request) {
        log.info("Enrolling user {} to course {}", request.getUserId(), request.getCourseId());

        BolatDinmukhamedUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                        "User with id " + request.getUserId() + " not found"));

        BolatDinmukhamedCourse course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                        "Course with id " + request.getCourseId() + " not found"));

        if (course.getAvailableSeats() <= 0) {
            throw new BolatDinmukhamedBadRequestException("No available seats in this course");
        }

        course.setAvailableSeats(course.getAvailableSeats() - 1);
        courseRepository.save(course);

        BolatDinmukhamedEnrollment enrollment = BolatDinmukhamedEnrollment.builder()
                .user(user)
                .course(course)
                .enrollmentDate(LocalDate.now())
                .status(BolatDinmukhamedEnrollmentStatus.ACTIVE)
                .build();

        return enrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }

    public BolatDinmukhamedEnrollmentResponse complete(Long enrollmentId) {
        log.info("Completing enrollment {}", enrollmentId);
        BolatDinmukhamedEnrollment enrollment = findEnrollmentById(enrollmentId);

        if (enrollment.getStatus() != BolatDinmukhamedEnrollmentStatus.ACTIVE) {
            throw new BolatDinmukhamedBadRequestException("Enrollment is not active");
        }

        enrollment.setStatus(BolatDinmukhamedEnrollmentStatus.COMPLETED);
        enrollment.setCompletionDate(LocalDate.now());

        return enrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }

    public BolatDinmukhamedEnrollmentResponse drop(Long enrollmentId) {
        log.info("Dropping enrollment {}", enrollmentId);
        BolatDinmukhamedEnrollment enrollment = findEnrollmentById(enrollmentId);

        if (enrollment.getStatus() != BolatDinmukhamedEnrollmentStatus.ACTIVE) {
            throw new BolatDinmukhamedBadRequestException("Enrollment is not active");
        }

        enrollment.setStatus(BolatDinmukhamedEnrollmentStatus.DROPPED);

        BolatDinmukhamedCourse course = enrollment.getCourse();
        course.setAvailableSeats(course.getAvailableSeats() + 1);
        courseRepository.save(course);

        return enrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }

    public void delete(Long id) {
        log.info("Deleting enrollment {}", id);
        enrollmentRepository.delete(findEnrollmentById(id));
    }

    public BolatDinmukhamedEnrollment findEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                        "Enrollment with id " + id + " not found"));
    }
}