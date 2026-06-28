package com.Tesis.Programacion.Service;


import com.Tesis.Programacion.Model.DTO.DTORequest.Auth.LoginRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Auth.LoginResponse;
import com.Tesis.Programacion.Model.Mapper.UsuarioMapper;
import com.Tesis.Programacion.Model.Usuario;
import com.Tesis.Programacion.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        Usuario usuario=usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if(!usuario.getActivo()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El usuario se encuentra deshabilitado");
        }

        String token=jwtService.generateToken(usuario);

        return new LoginResponse(token, UsuarioMapper.toDto(usuario));
    }
}
