package com.Tesis.Programacion.Model.DTO.DTOResponse.Venta;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente.ClienteResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Usuario.UsuarioResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class VentaResponse {
    private Long id;
    private VehiculoResponse vehiculo;
    private ClienteResponse cliente;
    private UsuarioResponse vendedor;
    private Double precioFinalDeVenta;
    private Double ganancia;
    private LocalDate fechaVenta;
}
