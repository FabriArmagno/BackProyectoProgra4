package com.Tesis.Programacion.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("VENTA")
public class HistorialVenta extends Historial{
    @ManyToOne
    private Usuario cliente;

    @ManyToOne
    private Usuario vendedor;
    private Double PrecioVenta;

}
