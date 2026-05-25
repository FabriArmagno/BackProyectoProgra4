package com.Tesis.Programacion.Model.DTO.DTORequest.Ventas;

import lombok.Getter;

@Getter
public class CrearVentaRequest {
    private Long vehiculoId;
    private Long clienteId;
    private Long vendedorId;
    private Double precioVenta;
}
