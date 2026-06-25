package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Enum.EnumResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerResponse;
import com.Tesis.Programacion.Model.Enums.EstadoReparacion;
import com.Tesis.Programacion.Model.Taller;

public class TallerMapper {

    public static TallerResponse toDto(Taller taller){
        return new TallerResponse(
                taller.getId(),
                new EnumResponse(taller.getEspecialidad().name(), taller.getEspecialidad().getLabel()),
                taller.getNombre(),
                taller.getActivo(),
                taller.getHistorialReparaciones().stream()
                        .filter(h -> h.getEstadoReparacion()!= EstadoReparacion.ENTREGADO)
                        .count(),
                taller.getDireccion(),
                UsuarioMapper.toDto(taller.getEncargadoTaller())
        );
    }

    public static TallerDetalleResponse toDetalleDto(Taller taller){
        TallerDetalleResponse tallerDetalleResponse=new TallerDetalleResponse();
        tallerDetalleResponse.setId(taller.getId());
        tallerDetalleResponse.setEspecialidad(new EnumResponse(taller.getEspecialidad().name(), taller.getEspecialidad().getLabel()));
        tallerDetalleResponse.setNombre(taller.getNombre());
        tallerDetalleResponse.setEncargadoTaller(UsuarioMapper.toDto(taller.getEncargadoTaller()));
        tallerDetalleResponse.setHistorialReparaciones(
                taller.getHistorialReparaciones()
                .stream()
                .map(ReparacionMapper::toDto)
                .toList()
        );
        tallerDetalleResponse.setDireccion(taller.getDireccion());
        tallerDetalleResponse.setActivo(taller.getActivo());

        return tallerDetalleResponse;
    }
}
