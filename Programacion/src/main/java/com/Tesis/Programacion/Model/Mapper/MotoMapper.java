package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Enum.EnumResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.Moto.MotoDetalleResponse;
import com.Tesis.Programacion.Model.Moto;

public class MotoMapper {

    public static MotoDetalleResponse toDetalleDTO(Moto moto) {
        MotoDetalleResponse motoResponse = new MotoDetalleResponse();

        motoResponse.setId(moto.getId());
        motoResponse.setPatente(moto.getPatente());
        motoResponse.setMarca(moto.getMarca());
        motoResponse.setModelo(moto.getModelo());
        motoResponse.setPrecio(moto.getPrecio());
        motoResponse.setColor(moto.getColor());
        motoResponse.setAnio(moto.getAnio());
        motoResponse.setKilometraje(moto.getKilometraje());
        motoResponse.setVersion(moto.getVersion());
        motoResponse.setDescripcion(moto.getDescripcion());
        motoResponse.setFechaIngreso(moto.getFechaIngreso());
        motoResponse.setTipo("MOTO");
        // Atributos de la Moto
        motoResponse.setTipoMoto(new EnumResponse(moto.getTipoMoto().name(), moto.getTipoMoto().getLabel()));
        motoResponse.setCilindrada(moto.getCilindrada());
        motoResponse.setImagenes(moto.getImagenes());

        return motoResponse;
    }
}

