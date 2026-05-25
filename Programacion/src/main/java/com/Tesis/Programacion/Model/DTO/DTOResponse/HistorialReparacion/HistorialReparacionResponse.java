package com.Tesis.Programacion.Model.DTO.DTOResponse.HistorialReparacion;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class HistorialReparacionResponse {
    private Long idTaller;
    private Long idVehiculo;
    private LocalDate fechaDeEntrada;
    private LocalDate fechaDeSalida;
    private String descripcion;
}
