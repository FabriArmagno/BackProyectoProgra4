package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.Vehiculo;
import com.Tesis.Programacion.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public Vehiculo createVehiculo(Vehiculo v){
        return vehiculoRepository.save(v);
    }

    public List<Vehiculo> getVehiculos(){
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> getVehiculoByPatente(String patente){
        return vehiculoRepository.findByPatente(patente);
    }

    public Optional<Vehiculo> getVehiculoByMarca(String marca){
        return vehiculoRepository.findByMarca(marca);
    }
}
