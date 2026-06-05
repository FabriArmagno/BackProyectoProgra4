package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerResponse;
import com.Tesis.Programacion.Model.Taller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TallerRepository extends JpaRepository<Taller,Long> {
    List<Taller> findByActivo(Boolean activo);
}
