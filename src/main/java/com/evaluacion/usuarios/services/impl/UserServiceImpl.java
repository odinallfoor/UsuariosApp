package com.evaluacion.usuarios.services.impl;

import com.evaluacion.usuarios.domain.UserResponse;
import com.evaluacion.usuarios.entities.UserEval;
import com.evaluacion.usuarios.exceptions.UserServiceExcepcion;
import com.evaluacion.usuarios.repositories.UserRepository;
import com.evaluacion.usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Value("${email.regex}")
    private String emailPattern;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserResponse> registerUser(UserEval userEval) {

        try{

            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(userEval.getEmail());

            if (!matcher.matches()) {
                throw new UserServiceExcepcion("El correo electrónico proporcionado no es válido");
            }

            if (userRepository.existsByEmail(userEval.getEmail())) {
                throw new UserServiceExcepcion("El correo electrónico ya está registrado");
            }

            UserResponse userResponse = UserResponse.builder()
                    .id(userEval.getId())
                    .token(userEval.getId().toString())
                    .created(LocalDateTime.now())
                    .modified(LocalDateTime.now())
                    .lastLogin(LocalDateTime.now())
                    .isActive(true)
                    .build();

            UserEval userEvalSaved = userRepository.save(userEval);

            if(userEvalSaved != null){
                return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
            } else {
                throw new UserServiceExcepcion("Error al guardar el usuario en la base de datos");
            }
        } catch (Exception ex){
            throw new UserServiceExcepcion("Ha ocurrido un error durante el registro del usuario. Causa: "
                    + ex.getMessage());
        }
    }
}
