package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {
    Boolean existsByPatenteIgnoreCase(String patente);
    List<Vehiculo>findByEstado(Estado estado);

    @Query("SELECT COUNT(v.id) FROM Vehiculo v WHERE v.estado=DISPONIBLE")
    Long countVehiculosDisponibles();
}
