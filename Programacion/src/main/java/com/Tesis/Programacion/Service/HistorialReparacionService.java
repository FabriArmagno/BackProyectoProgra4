package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.*;
import com.Tesis.Programacion.Model.DTO.DTORequest.Reparacion.CrearReparacionRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.HistorialReparacion.HistorialReparacionResponse;
import com.Tesis.Programacion.Model.Mapper.ReparacionMapper;
import com.Tesis.Programacion.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public Historial saveHistorialReparacion(HistorialReparacion historialReparacion) {
        return repository.save(historialReparacion);
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

    public List<HistorialReparacionResponse> getReparacionesPorTaller(Long idTaller) {
        return repository.findByTallerId(idTaller).stream()
                .map(ReparacionMapper::toDto)
                .toList();
    }

}