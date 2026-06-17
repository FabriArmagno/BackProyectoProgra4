package com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo;

import com.Tesis.Programacion.Model.Enums.Estado;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class VehiculoResponse {
    private Long id;
    private String marca;
    private String modelo;
    private String version;
    private Double precio;
    private String tipo;
    private Double kilometraje;
    private LocalDate fechaIngreso;
    private VehiculoEstadoResponse estado;
    private String patente;
    private int anio;
    private List<String> imagenes;
}
