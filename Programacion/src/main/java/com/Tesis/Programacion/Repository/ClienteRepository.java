package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findById(Long id);
    List<Cliente>findByActivo(Boolean activo);
    boolean existsByDni(Integer dni);
    boolean existsByEmail(String email);

    Long id(Long id);
}
