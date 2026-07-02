package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.*;
import com.Tesis.Programacion.Model.DTO.DTORequest.Reparacion.CambiarEstadoRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Reparacion.CrearReparacionRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.HistorialReparacion.HistorialReparacionResponse;
import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Model.Enums.EstadoReparacion;
import com.Tesis.Programacion.Model.Mapper.ReparacionMapper;
import com.Tesis.Programacion.Repository.*;
import jakarta.transaction.Transactional;
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
        return ReparacionMapper.toDto(encontrarReparacion(id));
    }

    public List<HistorialReparacionResponse> getReparacionesPorTaller(Long idTaller) {
        return repository.findByTallerId(idTaller).stream()
                .map(ReparacionMapper::toDto)
                .toList();
    }

    @Transactional
    public void cambiarEstado(Long id, CambiarEstadoRequest request){
        HistorialReparacion historialReparacion=encontrarReparacion(id);

        if(historialReparacion.getEstadoReparacion()==EstadoReparacion.ENTREGADO){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El vehículo ya fue entregado, no se puede modificar el estado");
        }

        if (request.getEstadoReparacion()==EstadoReparacion.ENTREGADO){
            Vehiculo vehiculo = vehiculoRepository.findById(historialReparacion.getVehiculo().getId())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado"));

            vehiculo.setEstado(Estado.DISPONIBLE);
            vehiculoRepository.save(vehiculo);
            historialReparacion.setFechaDeSalida(LocalDate.now());
        }

        historialReparacion.setEstadoReparacion(request.getEstadoReparacion());
        repository.save(historialReparacion);
    }

    public HistorialReparacion encontrarReparacion(Long id){
        return repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Reparacion no encontrada"));
    }
}