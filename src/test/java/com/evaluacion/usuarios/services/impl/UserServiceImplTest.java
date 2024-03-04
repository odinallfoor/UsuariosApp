package com.evaluacion.usuarios.services.impl;

import com.evaluacion.usuarios.domain.UserResponse;
import com.evaluacion.usuarios.entities.Phone;
import com.evaluacion.usuarios.entities.UserEval;
import com.evaluacion.usuarios.exceptions.UserServiceExcepcion;
import com.evaluacion.usuarios.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerUser_Success() {
        UserEval userEval = createUserEval_Success();

        when(userRepository.existsByEmail(userEval.getEmail()))
                .thenReturn(false);
        when(userRepository.save(userEval))
                .thenReturn(userEval);

        ResponseEntity<UserResponse> responseEntity = userService.registerUser(userEval);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void registerUser_BadEmail() {
        UserEval userEval = createUserEval_BadEmail();

        when(userRepository.existsByEmail(userEval.getEmail()))
                .thenReturn(false);
        when(userRepository.save(userEval))
                .thenReturn(userEval);

        assertThrows(UserServiceExcepcion.class, () -> userService.registerUser(userEval));
    }

    @Test
    public void testRegisterUser_DuplicateEmail() {
        UserEval userEval = createUserEval_Success();
        when(userRepository.existsByEmail(userEval.getEmail())).thenReturn(true);

        try {
            userService.registerUser(userEval);
        } catch (UserServiceExcepcion ex) {
            assertEquals("Ha ocurrido un error durante el registro del usuario. Causa: El correo electrónico ya está " +
                    "registrado", ex.getMessage());
        }
    }

    @Test
    public void testRegisterUser_DBError() {
        UserEval userEval = createUserEval_Success();

        when(userRepository.existsByEmail(userEval.getEmail()))
                .thenReturn(false);
        when(userRepository.save(userEval))
                .thenReturn(null);

        try {
            userService.registerUser(userEval);
        } catch (UserServiceExcepcion ex) {
            assertEquals("Ha ocurrido un error durante el registro del usuario. Causa: Error al guardar el usuario " +
                    "en la base de datos", ex.getMessage());
        }
    }

    private UserEval createUserEval_Success() {
        return UserEval.builder()
                .id(UUID.randomUUID())
                .name("Juan Rodriguez")
                .email("juan@rodriguez.org")
                .password("hunter2")
                .phones(Collections.singletonList(
                        Phone.builder()
                                .number("1234567")
                                .citycode("1")
                                .countrycode("57")
                                .build()))
                .build();
    }

    private UserEval createUserEval_BadEmail() {
        return UserEval.builder()
                .id(UUID.randomUUID())
                .name("Juan Rodriguez")
                .email("juan@rodriguez")
                .password("hunter2")
                .phones(Collections.singletonList(
                        Phone.builder()
                                .number("1234567")
                                .citycode("1")
                                .countrycode("57")
                                .build()))
                .build();
    }

}