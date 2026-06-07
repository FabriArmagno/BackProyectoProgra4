package com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Moto;

import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Model.Enums.TipoMoto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import javax.management.DescriptorAccess;
import java.time.LocalDate;

@Getter
public class CrearMotoRequest {
    @NotBlank(message = "esa patente no existe")
    private String patente;

    @NotBlank(message = "esa marca es inexistente")
    private String marca;

    @NotBlank(message = "ese modelo es incorrecto")
    private String modelo;

    @NotBlank(message = "esa version es incorrecta")
    private String version;

    @NotNull(message = "el precio es incorrecto")
    private Double precio;

    @NotBlank(message = "el color es incorrecto")
    private String color;

    @NotNull(message = "ese anio es incorrecto")
    private int anio;

    @NotNull(message = "el kilometraje es incorrecto")
    private Double kilometraje;

    @NotBlank(message = "ese motor es incorrecto")
    private String motor;

    @NotBlank(message = "esa combustion no existe")
    private String combustion;

    @NotBlank(message = "esa descripcion no es posible")
    private String descripcion;

    @NotBlank(message = "ese estado no existe")
    private Estado estado;

    @NotBlank(message = "esa cilindrada no es posible")
    private String cilindrada;

    @NotBlank(,message = "ese motor no es posible")
    private TipoMoto tipoMoto;
}
