package com.Tesis.Programacion.Model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo_historial"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HistorialVenta.class, name = "VENTA"),
        @JsonSubTypes.Type(value = HistorialReparacion.class, name = "REPARACION")
})

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_historial")
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "historial")
    private List<Vehiculo> vehiculo;

    private LocalDate fechaSalida;
}
