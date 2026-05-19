package com.lab.BolatDinmukhamed.repository;

import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BolatDinmukhamedUserRepository extends JpaRepository<BolatDinmukhamedUser, Long> {

    Optional<BolatDinmukhamedUser> findByUsername(String username);

    Optional<BolatDinmukhamedUser> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
