package com.Tesis.Programacion.Model.DTO.DTOResponse.Taller;

import com.Tesis.Programacion.Model.Enums.Especialidad;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TallerResponse {
    private Long id;
    private Especialidad especialidad;
    private String nombre;
    private Boolean activo;
}
