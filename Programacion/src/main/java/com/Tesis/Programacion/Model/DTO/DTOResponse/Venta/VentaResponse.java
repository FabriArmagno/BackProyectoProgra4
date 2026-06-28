package com.Tesis.Programacion.Model.DTO.DTOResponse.Venta;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class VentaResponse {
    private Long id;
    private Long vehiculoId;
    private Long clienteId;
    private Long vendedorId;
    private Double precioVenta;
    private LocalDate fechaVenta;
}
