package com.Tesis.Programacion.Model.DTO.DTORequest.Reparacion;

import com.Tesis.Programacion.Model.Enums.EstadoReparacion;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CambiarEstadoRequest {
    private EstadoReparacion estadoReparacion;
}
