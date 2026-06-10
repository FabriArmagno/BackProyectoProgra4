package com.Tesis.Programacion.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@DiscriminatorValue("AUTO")
@EqualsAndHashCode(callSuper = true)
public class Auto extends Vehiculo{
    private int puertas;
    private int potencia;
    private String tipoAuto;
    private String tipoDeTraccion;
    private String transmision;
}
