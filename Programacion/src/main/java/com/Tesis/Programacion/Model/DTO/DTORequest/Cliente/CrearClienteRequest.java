package com.Tesis.Programacion.Model.DTO.DTORequest.Cliente;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class CrearClienteRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 30)
    private String nombre;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 30)
    private String apellido;

    @NotNull(message = "El numero de documento es obligatorio")
    @Min(1000000)
    @Max(99999999)
    private Integer dni;

    @Email(message = "El email no cumple el formato valido")
    private String email;

    @NotNull(message = "El telefono es obligatorio")
    private String telefono;
}
