package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.Usuario;
import com.Tesis.Programacion.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario= usuarioRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getRol().name())
                .build();
    }
}
