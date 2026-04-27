package com.Tesis.Programacion.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@DiscriminatorValue("REPARACION")
public class HistorialReparacion extends Historial{
    @ManyToOne
    @JoinColumn(name = "taller_id")
    private Taller taller;
    private LocalDateTime fechaDeEntrada;
    private String descripcion;
}
