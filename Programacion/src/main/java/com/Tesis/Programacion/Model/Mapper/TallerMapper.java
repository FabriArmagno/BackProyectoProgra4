package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerResponse;
import com.Tesis.Programacion.Model.Taller;

public class TallerMapper {

    public TallerResponse toDto(Taller taller){
        return new TallerResponse(
                taller.getId(),
                taller.getEspecialidad(),
                taller.getNombre()
        );
    }

    public TallerDetalleResponse toDetalleDto(Taller taller){
        return new TallerDetalleResponse(
            taller.getId(),
                taller.getEspecialidad(),
                taller.getNombre(),
                taller.getEncargadoTaller(),
                taller.getDireccion(),
                taller.getHistorialReparaciones()
        );
    }
}
