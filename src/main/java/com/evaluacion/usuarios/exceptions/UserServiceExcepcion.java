package com.evaluacion.usuarios.exceptions;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Detalles sobre la excepción de servicio de usuario")
public class UserServiceExcepcion extends RuntimeException{

    public UserServiceExcepcion(String message){
        super(message);
    }

    public UserServiceExcepcion(String message, Throwable cause){
        super(message, cause);
    }
}
