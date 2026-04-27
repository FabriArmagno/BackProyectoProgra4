package com.Tesis.Programacion.Model;

import com.Tesis.Programacion.Model.Enums.Especialidad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taller {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    private String nombre;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario encargado;

    @OneToMany
    @JoinColumn(name = "taller_id")
    private List<Vehiculo> vehiculos;

    private String direccion;
}
