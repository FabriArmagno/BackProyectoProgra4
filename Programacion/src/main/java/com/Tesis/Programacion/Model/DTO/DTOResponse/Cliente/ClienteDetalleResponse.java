package com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Venta.VentaResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class ClienteDetalleResponse extends ClienteResponse{
    private String email;
    private List<VentaResponse>historialVentas;

    public ClienteDetalleResponse(Long id, String nombre, String apellido, Integer dni,String email,String telefono, Boolean activo, List<VentaResponse> historialVentas) {
        super(id, nombre, apellido, dni, activo, telefono);
        this.email = email;
        this.historialVentas = historialVentas;
    }
}
