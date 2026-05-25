package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.Cliente;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente.ClienteDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente.ClienteResponse;

public class ClienteMapper {

    ///Metodo para convertir una entidad a un DTO
    public static ClienteResponse toDto(Cliente cliente){
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDni(),
                cliente.getActivo()
        );
    }

    public static ClienteDetalleResponse toDetalleDto(Cliente cliente){
        return new ClienteDetalleResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDni(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getActivo(),
                cliente.getHistorialVentas()
        );
    }


}
