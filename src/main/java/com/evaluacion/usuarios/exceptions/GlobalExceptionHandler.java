package com.evaluacion.usuarios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserServiceExcepcion.class)
    public ResponseEntity<Object> handlerUserServiceException(UserServiceExcepcion ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage()));
    }

    static class ErrorResponse {

        private final String mensaje;

        public ErrorResponse(String mensaje){
            this.mensaje = mensaje;
        }

        public String getMensaje(){
            return this.mensaje;
        }
    }
}
