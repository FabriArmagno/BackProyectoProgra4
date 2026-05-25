package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.Auto;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Auto.AutoDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Auto.AutoResponse;

public class AutoMapper {

    ///Metodo para convertir una entidad a un DTO
    public static AutoResponse toDTO(Auto auto){
        return new AutoResponse(
                auto.getId(),
                auto.getPatente(),
                auto.getMarca(),
                auto.getModelo(),
                auto.getPrecio(),
                auto.getAño(),
                auto.getKilometraje(),
                auto.getEstado(),
                auto.getSubmodelo()
        );
    }

    public static AutoDetalleResponse toDetalleDTO(Auto auto){
        return new AutoDetalleResponse(
                auto.getId(),
                auto.getPatente(),
                auto.getMarca(),
                auto.getModelo(),
                auto.getPrecio(),
                auto.getAño(),
                auto.getKilometraje(),
                auto.getEstado(),
                auto.getSubmodelo(),
                auto.getColor(),
                auto.getMotor(),
                auto.getCombustion(),
                auto.getPotencia(),
                auto.getTipoAuto(),
                auto.getTipoDeTraccion(),
                auto.getTransmision(),
                auto.getHistorial()
        );
    }
}
