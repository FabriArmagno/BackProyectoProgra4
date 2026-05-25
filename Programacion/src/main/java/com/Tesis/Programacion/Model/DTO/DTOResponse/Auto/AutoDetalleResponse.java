package com.Tesis.Programacion.Model.DTO.DTOResponse.Auto;

import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Model.Historial;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AutoDetalleResponse {
    private Long id;
    private String patente;
    private String marca;
    private String modelo;
    private Double precio;
    private int año;
    private Double kilometraje;
    private Estado estado;
    private String submodelo;
    private String color;
    private String motor;
    private String combustion;
    private int potencia;
    private String tipoAuto;
    private String tipoDeTraccion;
    private String transmision;
    private List<Historial>historiales;
}
