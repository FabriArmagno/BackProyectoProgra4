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

    private Long dni;

    private String nombre;
    private String apellido;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private String email;

    private String password;

    private Boolean activo;
}
