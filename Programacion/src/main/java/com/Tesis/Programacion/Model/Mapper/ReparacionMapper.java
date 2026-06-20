package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.DTO.DTOResponse.HistorialReparacion.HistorialReparacionResponse;
import com.Tesis.Programacion.Model.HistorialReparacion;

public class ReparacionMapper {
    public static HistorialReparacionResponse toDto(HistorialReparacion historialReparacion){
        return new HistorialReparacionResponse(
                historialReparacion.getId(),
                historialReparacion.getTaller().getId(),
                historialReparacion.getVehiculo().getId(),
                historialReparacion.getFechaDeEntrada(),
                historialReparacion.getFechaDeSalida(),
                historialReparacion.getMotivo(),
                historialReparacion.getEstadoReparacion()
        );
    }
}
