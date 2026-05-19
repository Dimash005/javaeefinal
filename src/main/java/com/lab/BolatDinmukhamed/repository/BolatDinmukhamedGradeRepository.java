package com.lab.BolatDinmukhamed.repository;

import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BolatDinmukhamedGradeRepository extends JpaRepository<BolatDinmukhamedGrade, Long> {

    List<BolatDinmukhamedGrade> findByCourseId(Long courseId);

    List<BolatDinmukhamedGrade> findByUserId(Long userId);
}
