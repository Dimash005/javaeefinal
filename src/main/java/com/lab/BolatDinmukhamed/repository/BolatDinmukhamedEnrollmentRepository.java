package com.lab.BolatDinmukhamed.repository;

import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BolatDinmukhamedEnrollmentRepository extends JpaRepository<BolatDinmukhamedEnrollment, Long> {

    List<BolatDinmukhamedEnrollment> findByUserId(Long userId);
}
