package com.Tesis.Programacion.Model;

import com.Tesis.Programacion.Model.Enums.Estado;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(unique = true, nullable = false)
    private String patente;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private int anio;

    @Column(nullable = false)
    private Double kilometraje;

    @Column(nullable = false)
    private String motor;

    @Column(nullable = false)
    private String combustion;

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @Column(nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Historial> historial = new ArrayList<>();
}
