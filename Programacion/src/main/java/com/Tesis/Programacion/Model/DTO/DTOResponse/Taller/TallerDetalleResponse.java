package com.Tesis.Programacion.Model.DTO.DTOResponse.Taller;

import com.Tesis.Programacion.Model.Enums.Especialidad;
import com.Tesis.Programacion.Model.HistorialReparacion;
import com.Tesis.Programacion.Model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TallerDetalleResponse {
    private Long id;
    private Especialidad especialidad;
    private String nombre;
    private Usuario encargadoTaller;
    private String direccion;
    private List<HistorialReparacion> historialReparaciones;
    private Boolean activo;
}
