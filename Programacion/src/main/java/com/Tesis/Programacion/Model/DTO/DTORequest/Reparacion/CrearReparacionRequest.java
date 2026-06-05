package com.Tesis.Programacion.Model.DTO.DTORequest.Reparacion;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CrearReparacionRequest {
    private Long idTaller;
    private Long idVehiculo;
    private LocalDate fechaDeEntrada;
    private LocalDate fechaDeSalida;
    private String descripcion;
}
