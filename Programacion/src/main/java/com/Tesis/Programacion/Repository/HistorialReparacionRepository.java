package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.HistorialReparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialReparacionRepository extends JpaRepository<HistorialReparacion,Long> {
    @Query("SELECT COUNT(h) from HistorialReparacion h WHERE h.id=:tallerId AND h.estadoReparacion!='ENTREGADO'")
    Integer contarReparacionesActivas(@Param("tallerId")Long tallerId);
}
