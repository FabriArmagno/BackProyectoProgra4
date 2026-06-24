package com.Tesis.Programacion.Model.DTO.DTOResponse.HistorialReparacion;


import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoReparacionResponse;
import com.Tesis.Programacion.Model.Enums.EstadoReparacion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class HistorialReparacionResponse {
    private Long id;
    private Long idTaller;
    private VehiculoReparacionResponse vehiculo;
    private LocalDate fechaDeEntrada;
    private LocalDate fechaDeSalida;
    private String descripcion;
    private EstadoReparacion estadoReparacion;
}
