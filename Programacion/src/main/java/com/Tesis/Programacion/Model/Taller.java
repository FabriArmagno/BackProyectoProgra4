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
public class Taller {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especialidad especialidad;

    @Column(nullable = false)
    private String nombre;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario encargadoTaller;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private Boolean activo;

    @OneToMany(mappedBy = "taller", fetch = FetchType.LAZY)
    private List<HistorialReparacion> historialReparaciones;
}
