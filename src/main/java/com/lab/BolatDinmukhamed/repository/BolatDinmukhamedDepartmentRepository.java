package com.lab.BolatDinmukhamed.repository;

import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BolatDinmukhamedDepartmentRepository extends JpaRepository<BolatDinmukhamedDepartment, Long> {
}
