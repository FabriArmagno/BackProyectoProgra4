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
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>>getUsuarios(@RequestParam (required = false) Boolean activo){
        return ResponseEntity.ok(usuarioService.getUsuariosPorEstado(activo));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse>agregarUsuario(@Valid @RequestBody CrearUsuarioRequest crearUsuarioRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUser(crearUsuarioRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse>modificarUsuario(@Valid @RequestBody UpdateUsuarioRequest request, @PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.actualizarUsuario(request, id));
    }

    @GetMapping("/encargados")
    public ResponseEntity<List<UsuarioResponse>>getEncargados(){
        return ResponseEntity.ok(usuarioService.getEncargadosDeTaller());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>darDeBajaUsuario(@PathVariable Long id){
        usuarioService.bajaDeUsuario(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<Void>activarUsuario(@PathVariable Long id){
        usuarioService.activarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}
