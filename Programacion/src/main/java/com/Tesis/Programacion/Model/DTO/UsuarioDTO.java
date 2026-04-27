package com.Tesis.Programacion.Model.DTO;

import com.Tesis.Programacion.Model.Enums.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {

    @NotBlank
    @Size(min = 7, max = 8)
    private Long dni;

    @NotBlank
    @Size(min = 3, max = 30)
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 30)
    private String apellido;

    @NotBlank
    private Rol rol;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;
}
