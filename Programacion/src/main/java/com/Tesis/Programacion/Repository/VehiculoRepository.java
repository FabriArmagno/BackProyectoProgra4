package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {
    Optional<Vehiculo> findByPatente(String patente);

    Optional<Vehiculo> findByMarca(String marca);
}
