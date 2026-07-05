package com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CrearVehiculoRequest {
    @NotBlank(message = "La patente es obligatoria")
    @Pattern(
            regexp = "(?i)^\\s*([A-Z]{2}\\s*\\d{3}\\s*[A-Z]{2}|[A-Z]{3}\\s*\\d{3}|[A-Z]\\s*\\d{3}\\s*[A-Z]{3})\\s*$",
            message = "La patente no tiene un formato válido"
    )
    private String patente;

    @NotNull(message = "El precio de compra es obligatorio")
    private Double precioCompra;

    @NotNull(message = "El precio de venta es obligatorio")
    private Double precioVenta;

    @NotBlank(message = "El color es obligatorio")
    private String color;

    @NotNull(message = "El kilometraje es obligatorio")
    private Double kilometraje;

    private String descripcion;

    private List<String> imagenes = new ArrayList<>();
}
