package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Moto.CrearMotoRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.Moto.MotoDetalleResponse;
import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Model.Mapper.MotoMapper;
import com.Tesis.Programacion.Model.Moto;
import com.Tesis.Programacion.Repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class MotoService {

    @Autowired
    private MotoRepository repo;

    //crear moto
    public MotoDetalleResponse crearMoto(CrearMotoRequest request){

        Moto moto=new Moto();
        moto.setPatente(request.getPatente());
        moto.setMarca(request.getMarca());
        moto.setModelo(request.getModelo());
        moto.setPrecio(request.getPrecio());
        moto.setColor(request.getColor());
        moto.setAnio(request.getAnio());
        moto.setKilometraje(request.getKilometraje());
        moto.setVersion(request.getVersion());
        moto.setDescripcion(request.getDescripcion());
        moto.setFechaIngreso(LocalDate.now());
        moto.setEstado(Estado.DISPONIBLE);
        moto.setTipoMoto(request.getTipoMoto());
        moto.setCilindrada(request.getCilindrada());

        return MotoMapper.toDetalleDTO(moto);
    }

}
