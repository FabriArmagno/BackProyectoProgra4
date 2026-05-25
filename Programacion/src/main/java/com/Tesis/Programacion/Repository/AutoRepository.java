package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
}
