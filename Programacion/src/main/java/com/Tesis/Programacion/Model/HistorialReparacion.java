package com.Tesis.Programacion.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Entity
@Data
@DiscriminatorValue("REPARACION")
public class HistorialReparacion extends Historial{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taller_id", nullable = false)
    private Taller taller;

    @Column(nullable = false)
    private LocalDate fechaDeEntrada;

    private LocalDate fechaDeSalida;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;
}
