package com.lab.BolatDinmukhamed.service;

import com.lab.BolatDinmukhamed.dto.request.BolatDinmukhamedCourseRequest;
import com.lab.BolatDinmukhamed.dto.response.BolatDinmukhamedCourseResponse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedCourse;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedDepartment;
import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedProfessor;
import com.lab.BolatDinmukhamed.exception.BolatDinmukhamedResourceNotFoundException;
import com.lab.BolatDinmukhamed.mapper.BolatDinmukhamedCourseMapper;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedCourseRepository;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedDepartmentRepository;
import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BolatDinmukhamedCourseService {

    private final BolatDinmukhamedCourseRepository courseRepository;
    private final BolatDinmukhamedDepartmentRepository departmentRepository;
    private final BolatDinmukhamedProfessorRepository professorRepository;
    private final BolatDinmukhamedCourseMapper courseMapper;

    public Page<BolatDinmukhamedCourseResponse> getAll(String search, Long departmentId, Pageable pageable) {
        log.info("Fetching courses with search={}, departmentId={}, pageable={}", search, departmentId, pageable);

        Page<BolatDinmukhamedCourse> courses;

        if (search != null && !search.isBlank()) {
            courses = courseRepository.findByTitleContainingIgnoreCase(search, pageable);
        } else if (departmentId != null) {
            courses = courseRepository.findByDepartmentId(departmentId, pageable);
        } else {
            courses = courseRepository.findAll(pageable);
        }

        return courses.map(courseMapper::toResponse);
    }

    public BolatDinmukhamedCourseResponse getById(Long id) {
        log.info("Fetching course with id {}", id);
        return courseMapper.toResponse(findCourseById(id));
    }

    public BolatDinmukhamedCourseResponse create(BolatDinmukhamedCourseRequest request) {
        log.info("Creating course: {}", request.getTitle());
        BolatDinmukhamedCourse course = courseMapper.toEntity(request);

        if (request.getDepartmentId() != null) {
            BolatDinmukhamedDepartment department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                            "Department with id " + request.getDepartmentId() + " not found"));
            course.setDepartment(department);
        }

        if (request.getProfessorIds() != null && !request.getProfessorIds().isEmpty()) {
            Set<BolatDinmukhamedProfessor> professors = new HashSet<>(professorRepository.findAllById(request.getProfessorIds()));
            course.setProfessors(professors);
        }

        return courseMapper.toResponse(courseRepository.save(course));
    }

    public BolatDinmukhamedCourseResponse update(Long id, BolatDinmukhamedCourseRequest request) {
        log.info("Updating course with id {}", id);
        BolatDinmukhamedCourse course = findCourseById(id);
        courseMapper.updateEntity(course, request);

        if (request.getDepartmentId() != null) {
            BolatDinmukhamedDepartment department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                            "Department with id " + request.getDepartmentId() + " not found"));
            course.setDepartment(department);
        }

        if (request.getProfessorIds() != null) {
            Set<BolatDinmukhamedProfessor> professors = new HashSet<>(professorRepository.findAllById(request.getProfessorIds()));
            course.setProfessors(professors);
        }

        return courseMapper.toResponse(courseRepository.save(course));
    }

    public void delete(Long id) {
        log.info("Deleting course with id {}", id);
        courseRepository.delete(findCourseById(id));
    }

    public BolatDinmukhamedCourse findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new BolatDinmukhamedResourceNotFoundException(
                        "Course with id " + id + " not found"));
    }
}