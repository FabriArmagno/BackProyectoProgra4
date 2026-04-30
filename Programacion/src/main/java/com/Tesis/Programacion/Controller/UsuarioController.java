package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.UsuarioDTO;
import com.Tesis.Programacion.Model.Usuario;
import com.Tesis.Programacion.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>>getUsuarios(){
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Usuario>getUsuarioByDni(@PathVariable Long dni){
        return usuarioService.getUsuarioByDni(dni)
                .map(usuario -> ResponseEntity.ok().body(usuario))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/agregar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario>agregarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){

        Usuario usuario=new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setDni(usuarioDTO.getDni());
        usuario.setRol(usuarioDTO.getRol());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setActivo(true);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUser(usuario));
    }

    @PutMapping("/eliminar/{dni}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario>borrarUsuario(@PathVariable Long dni){
        return usuarioService.getUsuarioByDni(dni)
                .map(usuario -> ResponseEntity.ok().body(usuario))
                .orElse(ResponseEntity.notFound().build());
    }
}
