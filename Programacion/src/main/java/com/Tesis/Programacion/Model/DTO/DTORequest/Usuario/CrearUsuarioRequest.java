package com.Tesis.Programacion.Model.DTO.DTORequest.Usuario;

import com.Tesis.Programacion.Model.Enums.Rol;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CrearUsuarioRequest {

    @NotNull(message = "El numero de documento es obligatorio")
    @Min(1000000)
    @Max(99999999)
    private Integer dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 30)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 30)
    private String apellido;

    @NotNull(message = "El rol es obligatorio")
    private Rol rol;

    @Email(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatorio")
    @Size(min = 6)
    private String password;
}
