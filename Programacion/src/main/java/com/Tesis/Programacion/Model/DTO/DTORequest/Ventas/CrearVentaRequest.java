package com.Tesis.Programacion.Model.DTO.DTORequest.Ventas;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CrearVentaRequest {

    @NotNull(message = "El vehiculo es obligatorio")
    private Long vehiculoId;

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El vendedor es obligatorio")
    private Long vendedorId;

    @NotNull(message = "El precio de venta es obligatorio")
    private Double precioVenta;
}
