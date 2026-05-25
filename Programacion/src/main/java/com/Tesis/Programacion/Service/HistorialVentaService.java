package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.Historial;
import com.Tesis.Programacion.Repository.HistorialRepository;
import com.Tesis.Programacion.Repository.HistorialVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialVentaService {
    @Autowired
    private HistorialVentaRepository historialVentaRepository;

    private Historial createHistorial(Historial h){
        return historialVentaRepository.save(h);
    }

    private List<Historial> getHistoriales(){
        return historialVentaRepository.findAll();
    }

    private Optional<Historial> getHistorialByID(Long id){
        return historialVentaRepository.findById(id);
    }
}
