package com.Tesis.Programacion.Model.DTO.DTORequest.Taller;

import com.Tesis.Programacion.Model.Enums.Especialidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateTallerRequest {
    @NotNull(message = "La especialidad es obligatoria")
    private Especialidad especialidad;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El encargado es obligatorio")
    private Long idEncargadoTaller;

    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;
}
