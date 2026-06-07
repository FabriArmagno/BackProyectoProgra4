package com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public abstract class VehiculoDetalleResponse {
    private Long id;
    private String patente;
    private String marca;
    private String modelo;
    private String version;
    private Double precio;
    private String color;
    private int anio;
    private Double kilometraje;
    private String motor;
    private String combustion;
    private String tipo;
    private String descripcion;
    private LocalDate fechaIngreso;
}
