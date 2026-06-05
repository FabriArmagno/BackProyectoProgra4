package com.Tesis.Programacion.Model.DTO.DTOResponse.Auth;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Usuario.UsuarioResponse;
import com.Tesis.Programacion.Model.Usuario;

public class LoginResponse {
    private String token;
    private UsuarioResponse usuario;

    public LoginResponse(String token, UsuarioResponse usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }
}
