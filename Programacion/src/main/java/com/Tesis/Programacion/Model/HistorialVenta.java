package com.Tesis.Programacion.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@DiscriminatorValue("VENTA")
public class HistorialVenta extends Historial{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Usuario vendedor;

    @Column(nullable = false)
    private Double precioVenta;

    @Column(nullable = false)
    private LocalDate fechaVenta;

}
