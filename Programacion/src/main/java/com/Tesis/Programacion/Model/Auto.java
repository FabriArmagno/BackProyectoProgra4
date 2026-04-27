package com.Tesis.Programacion.Model;

import com.Tesis.Programacion.Model.Enums.TipoAuto;
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

    @Enumerated(EnumType.STRING)
    private TipoAuto tipoAuto;




}
