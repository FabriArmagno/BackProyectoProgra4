package com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Boolean activo;
    private String telefono;
}
