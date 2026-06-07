package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.Auto;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.Auto.AutoDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoResponse;
import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Model.Mapper.AutoMapper;
import com.Tesis.Programacion.Model.Mapper.VehiculoMapper;
import com.Tesis.Programacion.Model.Vehiculo;
import com.Tesis.Programacion.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<VehiculoResponse>getVehiculos(){
       return vehiculoRepository.findAll()
               .stream()
               .map(VehiculoMapper::toDto)
               .toList();
    }

    // Mostrar el detalle de un auto con el ID

    public VehiculoDetalleResponse getVehiculoById(Long id){
        Vehiculo vehiculo=vehiculoRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo encontrado"));

        if(vehiculo instanceof Auto auto){
            return AutoMapper.toDetalleDTO(auto);
        }

        throw new IllegalStateException("Tipo de vehiculo desconocido");
    }

    // Eliminar vehiculo

    public void eliminarVehiculo(Long id){
        if(!vehiculoRepository.existsById(id)){
            throw new RuntimeException("Vehiculo no encontrado");
        }
        vehiculoRepository.deleteById(id);
    }

    // Vender el vehiculo
    ///PENDIENTE. AGREGAR AL HISTORIAL DE VENTAS
    public String venderAuto(Long id){
        Vehiculo vehiculo=vehiculoRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Auto no encontrado"
                ));

        vehiculo.setEstado(Estado.VENDIDO);

        return "Vehiculo vendido con exito";
    }

    public Optional<Vehiculo> getVehiculoByPatente(String patente){
        return vehiculoRepository.findByPatente(patente);
    }

    public Optional<Vehiculo> getVehiculoByMarca(String marca){
        return vehiculoRepository.findByMarca(marca);
    }
}
