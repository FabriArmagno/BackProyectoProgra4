package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.Auto;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoEstadoResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoResponse;
import com.Tesis.Programacion.Model.Moto;
import com.Tesis.Programacion.Model.Vehiculo;

public abstract class VehiculoMapper {
    public static VehiculoResponse toDto(Vehiculo vehiculo){
        VehiculoResponse vehiculoResponse=new VehiculoResponse();

        vehiculoResponse.setId(vehiculo.getId());
        vehiculoResponse.setMarca(vehiculo.getMarca());
        vehiculoResponse.setModelo(vehiculo.getModelo());
        vehiculoResponse.setPrecio(vehiculo.getPrecio());
        vehiculoResponse.setImagenes(vehiculo.getImagenes());

        if(vehiculo instanceof Auto){
            vehiculoResponse.setTipo("AUTO");
        }

        if(vehiculo instanceof Moto){
            vehiculoResponse.setTipo("MOTO");
        }

        vehiculoResponse.setKilometraje(vehiculo.getKilometraje());
        vehiculoResponse.setFechaIngreso(vehiculo.getFechaIngreso());
        vehiculoResponse.setEstado(new VehiculoEstadoResponse(vehiculo.getEstado().name(), vehiculo.getEstado().getLabel()));
        vehiculoResponse.setPatente(vehiculo.getPatente());
        vehiculoResponse.setAnio(vehiculo.getAnio());
        vehiculoResponse.setVersion(vehiculo.getVersion());

        return vehiculoResponse;
    }
}
