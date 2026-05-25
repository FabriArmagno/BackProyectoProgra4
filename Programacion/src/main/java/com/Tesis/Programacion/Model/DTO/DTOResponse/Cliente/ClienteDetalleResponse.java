package com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente;

import com.Tesis.Programacion.Model.Historial;
import com.Tesis.Programacion.Model.HistorialVenta;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ClienteDetalleResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private String email;
    private String telefono;
    private Boolean activo;
    private List<HistorialVenta> historialVentas;
}
