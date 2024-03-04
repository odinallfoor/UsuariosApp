package com.evaluacion.usuarios.controller;

import com.evaluacion.usuarios.domain.UserResponse;
import com.evaluacion.usuarios.entities.UserEval;
import com.evaluacion.usuarios.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Solicita la creación de un nuevo usuario en la base de dato", responses = {
            @ApiResponse(responseCode = "500", description = "Error de sistema", content = {
                    @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            }),})
    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserEval userEval){

        return userService.registerUser(userEval);
    }
}
