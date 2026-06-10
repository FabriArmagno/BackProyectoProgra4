package com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Moto;

import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Model.Enums.TipoMoto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import javax.management.DescriptorAccess;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @NotBlank(message = "El color es obligatorio")
    private String color;

    @NotNull(message = "El año es obligatorio")
    private int anio;

    @NotNull(message = "El kilometraje es obligatorio")
    private Double kilometraje;

    @NotBlank(message = "El motor es obligatorio")
    private String motor;

    @NotBlank(message = "La combustion es obligatorio")
    private String combustion;

    @NotBlank(message = "La descripcion es obligatorio")
    private String descripcion;

    @NotNull(message = "La cilindrada es obligatorio")
    private int cilindrada;

    @NotNull(message = "El tipo de moto es obligatorio")
    private TipoMoto tipoMoto;

    private List<String> imagenes = new ArrayList<>();
}
