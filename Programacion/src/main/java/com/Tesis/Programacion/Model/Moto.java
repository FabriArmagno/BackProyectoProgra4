package com.Tesis.Programacion.Model;

import com.Tesis.Programacion.Model.Enums.TipoMoto;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@DiscriminatorValue("MOTO")
@EqualsAndHashCode(callSuper = true)
public class Moto extends Vehiculo{

    private int cilindrada;

    private TipoMoto tipoMoto;
}
