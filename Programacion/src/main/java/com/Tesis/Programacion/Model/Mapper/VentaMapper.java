package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Venta.VentaResponse;
import com.Tesis.Programacion.Model.HistorialVenta;

public class VentaMapper {
    public static VentaResponse toDto(HistorialVenta ventaCruda){
        return new VentaResponse(
                ventaCruda.getId(),
                ventaCruda.getVehiculo().getId(),
                ventaCruda.getCliente().getId(),
                ventaCruda.getVendedor().getId(),
                ventaCruda.getPrecioVenta(),
                ventaCruda.getFechaVenta()
        );
    }
}
