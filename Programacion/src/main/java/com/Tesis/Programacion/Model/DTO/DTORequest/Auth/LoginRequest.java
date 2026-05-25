package com.Tesis.Programacion.Model.DTO.DTORequest.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @Email(message = "Formato invalido")
    @NotBlank(message = "El email no puede estar vacio")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 6)
    private String password;
}
