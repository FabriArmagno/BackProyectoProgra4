package com.Tesis.Programacion.Model.DTO;

import com.Tesis.Programacion.Model.Enums.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    @NotNull
    @Min(1000000)
    @Max(99999999)
    @Column(unique = true)
    private Long dni;

    @NotBlank
    @Size(min = 3, max = 30)
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 30)
    private String apellido;

    @NotNull
    private Rol rol;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;
}
