package com.Tesis.Programacion.Model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(unique = true)
    private String patente;
    private String marca;
    private String modelo;
    private Double precio;
    private String color;
    private int año;
    private Double kilometraje;
    private String motor;
    private String combustion;
    private LocalDate fechaIngreso;
    private boolean enReparacion;
    private boolean vendido;

    @ManyToOne
    @JoinColumn(name = "historial_id")
    private Historial historial;
}
