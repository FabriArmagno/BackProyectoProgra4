package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.HistorialReparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialReparacionRepository extends JpaRepository<HistorialReparacion,Long> {
}
