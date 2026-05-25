package com.Tesis.Programacion.Model.Mapper;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Usuario.UsuarioResponse;
import com.Tesis.Programacion.Model.Usuario;

public class UsuarioMapper {

    ///Metodo para convertir una entidad a un DTO
    public static UsuarioResponse toDto(Usuario usuario){
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getDni(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getRol(),
                usuario.getEmail(),
                usuario.getActivo()
        );
    }
}
