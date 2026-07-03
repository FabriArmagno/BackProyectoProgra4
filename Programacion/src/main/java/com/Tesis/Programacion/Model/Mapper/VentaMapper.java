package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Venta.VentaResponse;
import com.Tesis.Programacion.Model.HistorialVenta;

public class VentaMapper {
    public static VentaResponse toDto(HistorialVenta ventaCruda){
        return new VentaResponse(
                ventaCruda.getId(),
                VehiculoMapper.toDto(ventaCruda.getVehiculo()),
                ClienteMapper.toDto(ventaCruda.getCliente()),
                UsuarioMapper.toDto(ventaCruda.getVendedor()),
                ventaCruda.getPrecioFinalVenta(),
                ventaCruda.getPrecioFinalVenta()-ventaCruda.getPrecioCompra(),
                ventaCruda.getFechaVenta()
        );
    }
}
