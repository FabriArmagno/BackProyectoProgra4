package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRepository extends JpaRepository<Historial,Long> {
}
