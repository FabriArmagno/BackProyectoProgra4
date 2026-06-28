package com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Moto;

import com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.CrearVehiculoRequest;
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
public class CrearMotoRequest extends CrearVehiculoRequest {

    @NotBlank(message = "esa marca es inexistente")
    private String marca;

    @NotBlank(message = "ese modelo es incorrecto")
    private String modelo;

    @NotBlank(message = "esa version es incorrecta")
    private String version;

    @NotNull(message = "El año es obligatorio")
    private Integer anio;

    @NotBlank(message = "El motor es obligatorio")
    private String motor;

    @NotBlank(message = "La combustion es obligatorio")
    private String combustion;

    @NotNull(message = "La cilindrada es obligatorio")
    private Integer cilindrada;

    @NotNull(message = "El tipo de moto es obligatorio")
    private TipoMoto tipoMoto;
}
