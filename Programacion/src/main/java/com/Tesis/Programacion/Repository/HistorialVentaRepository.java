package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.Historial;
import com.Tesis.Programacion.Model.HistorialVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialVentaRepository extends JpaRepository<HistorialVenta,Long> {
}
