package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTORequest.Usuario.CrearUsuarioRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Usuario.UpdateUsuarioRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Usuario.UsuarioResponse;
import com.Tesis.Programacion.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponse>>getUsuarios(){
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse>agregarUsuario(@Valid @RequestBody CrearUsuarioRequest crearUsuarioRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUser(crearUsuarioRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse>modificarUsuario(@Valid @RequestBody UpdateUsuarioRequest request, @PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.actualizarUsuario(request, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse>darDeBajaUsuario(@PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.bajaDeUsuario(id));
    }

    @PatchMapping("/{id}/activar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse>activarUsuario(@PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.activarUsuario(id));
    }
}
