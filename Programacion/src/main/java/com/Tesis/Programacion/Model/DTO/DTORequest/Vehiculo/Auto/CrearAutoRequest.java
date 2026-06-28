package com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Auto;

import com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.CrearVehiculoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

@Getter
public class CrearAutoRequest extends CrearVehiculoRequest {

    @NotNull
    private Long idTrim;
}
