package com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Boolean activo;
}
