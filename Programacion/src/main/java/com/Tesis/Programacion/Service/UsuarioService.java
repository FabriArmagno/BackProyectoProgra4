package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.Usuario;
import com.Tesis.Programacion.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUser(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario>getUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioByDni(Long dni){
        return usuarioRepository.findByDni(dni);
    }

    public void deleteByDni(Usuario usuario){
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }
}
