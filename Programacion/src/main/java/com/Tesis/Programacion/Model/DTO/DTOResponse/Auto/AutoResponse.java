package com.Tesis.Programacion.Model.DTO.DTOResponse.Auto;

import com.Tesis.Programacion.Model.Enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AutoResponse {
    private Long id;
    private String patente;
    private String marca;
    private String modelo;
    private Double precio;
    private int año;
    private Double kilometraje;
    private Estado estado;
    private String submodelo;
}
