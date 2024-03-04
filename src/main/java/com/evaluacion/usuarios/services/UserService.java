package com.evaluacion.usuarios.services;

import com.evaluacion.usuarios.domain.UserResponse;
import com.evaluacion.usuarios.entities.UserEval;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<UserResponse> registerUser(UserEval userEval);

}
