package com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.Moto;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoDetalleResponse;
import com.Tesis.Programacion.Model.Enums.TipoMoto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MotoDetalleResponse extends VehiculoDetalleResponse {
    private int cilindrada;
    private TipoMoto tipoMoto;
}
