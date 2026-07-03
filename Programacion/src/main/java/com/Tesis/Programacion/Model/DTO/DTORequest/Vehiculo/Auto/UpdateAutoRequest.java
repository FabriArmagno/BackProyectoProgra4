package com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Auto;

import com.Tesis.Programacion.Model.Enums.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateAutoRequest {

    @NotNull
    private Long idTrim;

    @NotNull(message = "El precio de compra es obligatorio")
    private Double precioCompra;

    @NotNull(message = "El precio de venta es obligatorio")
    private Double precioVenta;

    @NotNull(message = "El kilometraje es obligatorio")
    private Double kilometraje;

    @NotBlank(message = "La patente es obligatoria")
    private String patente;

    @NotBlank(message = "El color es obligatorio")
    private String color;

    private String descripcion;
}
