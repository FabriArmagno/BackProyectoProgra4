package com.Tesis.Programacion.Model.DTO;

import lombok.Data;

@Data
public class CrearVehiculoDTO {
    private Long idTrim;
    private Double precio;
    private Double kilometraje;
    private String patente;
    private String color;

}
