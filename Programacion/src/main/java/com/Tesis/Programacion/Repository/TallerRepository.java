package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerResponse;
import com.Tesis.Programacion.Model.Taller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface TallerRepository extends JpaRepository<Taller,Long> {
    List<Taller> findByActivo(Boolean activo);
    Optional<Taller> findByIdAndActivoTrue(Long id);
}
