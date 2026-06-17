package com.Tesis.Programacion.Model;

import com.Tesis.Programacion.Model.Enums.EstadoReparacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("REPARACION")
public class HistorialReparacion extends Historial{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taller_id")
    private Taller taller;

    private LocalDate fechaDeEntrada;

    private LocalDate fechaDeSalida;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    @Enumerated(EnumType.STRING)
    private EstadoReparacion estadoReparacion;
}
