package com.Tesis.Programacion.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@DiscriminatorValue("AUTO")
@EqualsAndHashCode(callSuper = true)
public class Auto extends Vehiculo{

    @Column(nullable = false)
    private int puertas;

    @Column(nullable = false)
    private int potencia;

    @Column(nullable = false)
    private String tipoAuto;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String tipoDeTraccion;

    @Column(nullable = false)
    private String transmision;
}
