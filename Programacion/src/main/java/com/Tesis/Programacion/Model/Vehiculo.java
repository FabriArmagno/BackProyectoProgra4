package com.Tesis.Programacion.Model;

import com.Tesis.Programacion.Model.Enums.Combustion;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo_vehiculo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Auto.class, name = "AUTO"),
        @JsonSubTypes.Type(value = Moto.class, name = "MOTO")
})

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_vehiculo", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patente;
    private String marca;
    private String modelo;
    private Long precio;
    private String color;
    private int año;
    private Long kilometraje;
    private String motor;

    @Enumerated(EnumType.STRING)
    private Combustion combustion;
    private String descripcion;
    private LocalDate fechaIngreso;
    private boolean enReparacion;
    private boolean vendido;

    @ManyToOne
    @JoinColumn(name = "historial_id")
    private Historial historial;
}
