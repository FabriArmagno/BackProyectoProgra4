package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.HistorialVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistorialVentaRepository extends JpaRepository<HistorialVenta, Long> {

    // Para listar las ventas de un empleado en específico por su username
    List<HistorialVenta> findByVendedorEmail(String email);

    // Para contar solo las ventas de ese empleado
    Long countByVendedorUsername(String username);
}
