package com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo;

import com.Tesis.Programacion.Model.DTO.DTOResponse.HistorialReparacion.HistorialReparacionResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class VehiculoDetalleResponse extends VehiculoResponse{
    private String color;
    private String motor;
    private String combustion;
    private String descripcion;
    private List<String> imagenes;
    private List<HistorialReparacionResponse>historialDeReparacion;
}
