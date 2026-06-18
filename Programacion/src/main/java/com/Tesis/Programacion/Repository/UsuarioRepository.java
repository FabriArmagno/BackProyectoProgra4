package com.Tesis.Programacion.Repository;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Usuario.UsuarioResponse;
import com.Tesis.Programacion.Model.Enums.Rol;
import com.Tesis.Programacion.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.instrument.classloading.ResourceOverridingShadowingClassLoader;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario>findByEmail(String email);
    boolean existsByDni(Integer dni);
    boolean existsByEmail(String email);
    List<Usuario> findByRolAndActivoTrue(Rol rol);
    List<Usuario> findByActivo(Boolean activo);
}
