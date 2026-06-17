package com.Tesis.Programacion.Model.DTO.DTORequest.Taller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AsignarTallerRequest {

    @NotNull(message = "El vehiculo es oblitario")
    private Long idVehiculo;

    @NotNull(message = "El taller es obligatorio")
    private Long idTaller;

    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;
}
