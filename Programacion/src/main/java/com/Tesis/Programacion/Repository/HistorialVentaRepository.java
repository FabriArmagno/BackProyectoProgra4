package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.HistorialVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistorialVentaRepository extends JpaRepository<HistorialVenta, Long> {

    // Para listar las ventas de un empleado en específico por su username
    List<HistorialVenta> findByVendedorEmail(String email);
    List<HistorialVenta>findByVendedorId(Long id);
    // Para contar solo las ventas de ese empleado
    Long countByVendedorEmail(String email);
    List<HistorialVenta>findByFechaVentaBetween(LocalDate desde, LocalDate hasta);
    List<HistorialVenta>findByVendedorIdAndFechaVentaBetween(Long empleadoId, LocalDate desde, LocalDate hasta);
}
