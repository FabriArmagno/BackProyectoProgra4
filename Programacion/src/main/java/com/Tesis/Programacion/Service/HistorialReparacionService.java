package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.*;
import com.Tesis.Programacion.Model.DTO.DTORequest.Reparacion.CrearReparacionRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.HistorialReparacion.HistorialReparacionResponse;
import com.Tesis.Programacion.Model.Mapper.ReparacionMapper;
import com.Tesis.Programacion.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HistorialReparacionService {
    @Autowired
    private HistorialReparacionRepository repository;

    @Autowired
    private TallerRepository tallerRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    //CRUD
    public Historial saveHistorialReparacion(CrearReparacionRequest reparacionRequest) {
        boolean validarfecha = fechasValidas(reparacionRequest.getFechaDeEntrada(), reparacionRequest.getFechaDeSalida());

        Vehiculo vId = vehiculoRepository.findById(reparacionRequest.getIdVehiculo()).orElse(null);
        Taller  tId = tallerRepository.findById(reparacionRequest.getIdTaller()).orElse(null);

        if (vId != null && tId != null && validarfecha){
            return HistorialReparacion.builder()
                    .taller(tId)
                    .fechaDeEntrada(reparacionRequest.getFechaDeEntrada())
                    .fechaDeSalida(reparacionRequest.getFechaDeSalida())
                    .descripcion(reparacionRequest.getDescripcion())
                    .vehiculo(vId)
                    .build();
        }else{
            throw new RuntimeException("Ids Invalidos");
        }
    }

    public List<HistorialReparacionResponse> getReparaciones(){
        return repository.findAll().stream()
                .map(ReparacionMapper::toDto)
                .toList();
    }

    public HistorialReparacionResponse getReparacionByID(Long id){
        return repository.findById(id)
                .map(ReparacionMapper::toDto)
                .orElse(null);
    }

    public static boolean fechasValidas(LocalDate entrada, LocalDate salida) {

        // Verificar null
        if (entrada == null || salida == null) {
            return false;
        }

        // La salida no puede ser antes de la entrada
        if (salida.isBefore(entrada)) {
            return false;
        }

        // Opcional: no permitir mismo día
        if (salida.isEqual(entrada)) {
            return false;
        }

        return true;
    }

}
