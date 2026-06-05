package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTORequest.Auth.LoginRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Auth.LoginResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Usuario.UsuarioResponse;
import com.Tesis.Programacion.Model.Mapper.UsuarioMapper;
import com.Tesis.Programacion.Model.Usuario;
import com.Tesis.Programacion.Service.JwtService;
import com.Tesis.Programacion.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse>login(@Valid @RequestBody LoginRequest loginRequest){

        //Autenticamos al usuario con nombre y contraseña
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        //Obtenemos los detalles del usuario
        UserDetails user= userDetailsService.loadUserByUsername(loginRequest.getEmail());

        //Generamos el token JWT
        String token= jwtService.generateToken(user);

        Usuario usuario=usuarioService.encontrarUsuarioByEmail(loginRequest.getEmail());

        // Devolvemos el token en la respuesta
        return ResponseEntity.ok(new LoginResponse(token, UsuarioMapper.toDto(usuario)));
    }
}
