package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerResponse;
import com.Tesis.Programacion.Model.HistorialReparacion;
import com.Tesis.Programacion.Model.Taller;

public class TallerMapper {

    public static TallerResponse toDto(Taller taller){
        return new TallerResponse(
                taller.getId(),
                taller.getEspecialidad(),
                taller.getNombre(),
                taller.getActivo()
        );
    }

    public static TallerDetalleResponse toDetalleDto(Taller taller){
        TallerDetalleResponse tallerDetalleResponse=new TallerDetalleResponse();
        tallerDetalleResponse.setId(taller.getId());
        tallerDetalleResponse.setEspecialidad(taller.getEspecialidad());
        tallerDetalleResponse.setNombre(taller.getNombre());
        tallerDetalleResponse.setEncargadoTaller(taller.getEncargadoTaller());
        tallerDetalleResponse.setDireccion(taller.getDireccion());
        tallerDetalleResponse.setHistorialReparaciones(taller.getHistorialReparaciones());
        tallerDetalleResponse.setActivo(taller.getActivo());

        return tallerDetalleResponse;
    }
}
