package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.LoginRequest;
import com.Tesis.Programacion.Model.DTO.LoginResponse;
import com.Tesis.Programacion.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest loginRequest){

        //Autenticamos al usuario con nombre y contraseña
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        //Obtenemos los detalles del usuario
        UserDetails user= userDetailsService.loadUserByUsername(loginRequest.getUsername());

        //Generamos el token JWT
        String token= jwtService.generateToken(user);

        // Devolvemos el token en la respuesta
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
