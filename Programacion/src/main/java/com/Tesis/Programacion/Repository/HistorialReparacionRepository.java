package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.HistorialReparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialReparacionRepository extends JpaRepository<HistorialReparacion,Long> {
    @Query("SELECT COUNT(h) from HistorialReparacion h WHERE h.taller.id = :tallerId AND h.estadoReparacion != 'ENTREGADO'")
    Integer contarReparacionesActivas(@Param("tallerId") Long tallerId);

    List<HistorialReparacion> findByTallerId(Long tallerId);
}
