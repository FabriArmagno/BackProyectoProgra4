package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Repository.ClienteRepository;
import com.Tesis.Programacion.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidacionesService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Metodo para validar que el DNI no exista
    public void validarDni(Integer dni){
        if (usuarioRepository.existsByDni(dni) || clienteRepository.existsByDni(dni)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El DNI ya esta registrado");
        }
    }

    // Metodo para validar que el email no exista
    public void validarEmail(String email){
        if (usuarioRepository.existsByEmailIgnoreCase(email.trim()) || clienteRepository.existsByEmailIgnoreCase(email.trim())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya esta registrado");
        }
    }

    //Metodo para validar si el email ya existe(se usa para validar en el front)
    public Boolean existeEmail(String email){
        String emailLimpio=email!=null ? email.trim() : "";
        return usuarioRepository.existsByEmailIgnoreCase(emailLimpio) || clienteRepository.existsByEmailIgnoreCase(emailLimpio);
    }

    //Metodo para validar si el dni ya existe(se usa para validar en el front)
    public Boolean existeDni(Integer dni){
        return usuarioRepository.existsByDni(dni) || clienteRepository.existsByDni(dni);
    }
}
