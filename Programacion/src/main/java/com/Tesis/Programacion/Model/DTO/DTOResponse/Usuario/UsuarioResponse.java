package com.Tesis.Programacion.Model.DTO.DTOResponse.Usuario;

import com.Tesis.Programacion.Model.Enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioResponse {
    private Long id;
    private Integer dni;
    private String nombre;
    private String apellido;
    private Rol rol;
    private String email;
    private boolean activo;
}
