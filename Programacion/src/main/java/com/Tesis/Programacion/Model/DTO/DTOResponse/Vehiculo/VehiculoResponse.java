package com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Enum.EnumResponse;
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
    private EnumResponse estado;
    private String patente;
    private int anio;
    private List<String> imagenes;
}
