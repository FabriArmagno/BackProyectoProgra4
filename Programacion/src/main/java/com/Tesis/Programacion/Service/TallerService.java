package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.Taller;
import com.Tesis.Programacion.Repository.TallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TallerService {
    @Autowired
    private TallerRepository tallerRepository;

    private Taller createTaller(Taller t){
        return tallerRepository.save(t);
    }

    private List<Taller>getTalleres(){
        return tallerRepository.findAll();
    }

    private Optional<Taller>getTallerByID(Long id){
        return tallerRepository.findById(id);
    }

    private boolean deleteTaller(Long id){
        if(tallerRepository.existsById(id)) {
            tallerRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
