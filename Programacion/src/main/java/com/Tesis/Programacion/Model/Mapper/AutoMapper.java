package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.Auto;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.Auto.AutoDetalleResponse;

public class AutoMapper {

    ///Metodo para convertir una entidad a un DTO
   public static AutoDetalleResponse toDetalleDTO(Auto auto){
       AutoDetalleResponse autoResponse=new AutoDetalleResponse();
       autoResponse.setId(auto.getId());
       autoResponse.setPatente(auto.getPatente());
       autoResponse.setMarca(auto.getMarca());
       autoResponse.setModelo(auto.getModelo());
       autoResponse.setPrecio(auto.getPrecio());
       autoResponse.setColor(auto.getColor());
       autoResponse.setAnio(auto.getAnio());
       autoResponse.setKilometraje(auto.getKilometraje());
       autoResponse.setMotor(auto.getMotor());
       autoResponse.setCombustion(auto.getCombustion());
       autoResponse.setTipo("AUTO");
       autoResponse.setPuertas(auto.getPuertas());
       autoResponse.setPotencia(auto.getPotencia());
       autoResponse.setTipoAuto(auto.getTipoAuto());
       autoResponse.setDescripcion(auto.getDescripcion());
       autoResponse.setTipoDeTraccion(auto.getTipoDeTraccion());
       autoResponse.setTransmision(auto.getTransmision());
       autoResponse.setFechaIngreso(auto.getFechaIngreso());
       autoResponse.setVersion(auto.getVersion());

       return autoResponse;
    }
}
