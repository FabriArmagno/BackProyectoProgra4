package com.Tesis.Programacion.Model;

import com.Tesis.Programacion.Model.Enums.Rol;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long dni;

    private String nombre;
    private String apellido;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private Boolean activo;
}
