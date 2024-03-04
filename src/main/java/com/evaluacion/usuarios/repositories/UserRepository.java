package com.evaluacion.usuarios.repositories;

import com.evaluacion.usuarios.entities.UserEval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEval, UUID> {

    boolean existsByEmail(String email);
}
