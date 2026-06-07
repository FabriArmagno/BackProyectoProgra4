package com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.Auto;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoDetalleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AutoDetalleResponse extends VehiculoDetalleResponse {
    private int puertas;
    private int potencia;
    private String tipoAuto;
    private String tipoDeTraccion;
    private String transmision;
}
