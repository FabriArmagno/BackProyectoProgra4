package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.Taller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TallerRepository extends JpaRepository<Taller,Long> {
}
