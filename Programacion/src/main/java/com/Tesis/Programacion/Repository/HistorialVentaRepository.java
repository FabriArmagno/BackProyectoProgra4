package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.HistorialVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistorialVentaRepository extends JpaRepository<HistorialVenta, Long> {

    // Para listar las ventas de un empleado en específico por su username
    List<HistorialVenta> findByVendedorEmail(String email);
    List<HistorialVenta>findByVendedorId(Long id);
    List<HistorialVenta>findByFechaVentaBetween(LocalDate desde, LocalDate hasta);
    List<HistorialVenta>findByVendedorIdAndFechaVentaBetween(Long vendedorId, LocalDate desde, LocalDate hasta);

    @Query("SELECT SUM(v.precioFinalVenta-v.precioCompra) FROM HistorialVenta v WHERE v.fechaVenta BETWEEN :inicio AND :fin")
    Double getFacturacionDelMes(LocalDate inicio, LocalDate fin);

    @Query("SELECT COUNT(v.id) FROM HistorialVenta v WHERE v.fechaVenta BETWEEN :inicio AND :fin")
    Long getVentasDelMes(LocalDate inicio, LocalDate fin);

    @Query("SELECT COUNT(v.id) FROM HistorialVenta v WHERE v.vendedor.id=:id AND v.fechaVenta BETWEEN :inicio AND :fin")
    Long getVentasDelMesPorEmpleado(LocalDate inicio, LocalDate fin, Long id);

    @Query("SELECT SUM(v.precioFinalVenta-v.precioCompra) FROM HistorialVenta v WHERE v.vendedor.id=:id AND v.fechaVenta BETWEEN :inicio AND :fin")
    Double getFacturacionDelMesPorEmpleado(LocalDate inicio, LocalDate fin, Long id);

    List<HistorialVenta>findTop3ByVendedorIdOrderByFechaVentaDesc(Long idVendedor);
}
