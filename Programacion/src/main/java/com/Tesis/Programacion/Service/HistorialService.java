package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.Historial;
import com.Tesis.Programacion.Repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialService {
    @Autowired
    private HistorialRepository historialRepository;

    private Historial createHistorial(Historial h){
        return historialRepository.save(h);
    }

    private List<Historial> getHistoriales(){
        return historialRepository.findAll();
    }

    private Optional<Historial> getHistorialByID(Long id){
        return historialRepository.findById(id);
    }
}
