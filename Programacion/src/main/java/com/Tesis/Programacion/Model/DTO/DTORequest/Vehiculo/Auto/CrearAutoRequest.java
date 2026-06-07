package com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Auto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

@Getter
public class CrearAutoRequest {

    @NotNull
    private Long idTrim;

    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    @NotNull(message = "El kilometraje es obligatorio")
    private Double kilometraje;

    @NotBlank(message = "La patente es obligatoria")
    private String patente;

    @NotBlank(message = "El color es obligatorio")
    private String color;

    private List<String> imagenes = new ArrayList<>();
}
