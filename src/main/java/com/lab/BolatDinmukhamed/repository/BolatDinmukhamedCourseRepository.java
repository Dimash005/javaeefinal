package com.lab.BolatDinmukhamed.repository;

import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BolatDinmukhamedCourseRepository extends JpaRepository<BolatDinmukhamedCourse, Long> {

    Page<BolatDinmukhamedCourse> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<BolatDinmukhamedCourse> findByDepartmentId(Long departmentId, Pageable pageable);
}
