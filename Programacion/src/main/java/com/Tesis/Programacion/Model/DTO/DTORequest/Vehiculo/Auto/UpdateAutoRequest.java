package com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Auto;

import com.Tesis.Programacion.Model.Enums.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateAutoRequest {

    @NotBlank
    private String patente;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotNull
    private Double precio;

    @NotNull
    private int año;

    @NotNull
    private Double kilometraje;

    @NotBlank
    private Estado estado;

    @NotBlank
    private String submodelo;

    @NotBlank
    private String color;

    @NotBlank
    private String motor;

    @NotBlank
    private String combustion;

    @NotNull
    private int potencia;

    @NotBlank
    private String tipoAuto;

    @NotBlank
    private String tipoDeTraccion;

    @NotBlank
    private String transmision;
}
