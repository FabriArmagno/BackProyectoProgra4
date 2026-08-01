package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTORequest.Cliente.ClienteRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente.ClienteDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente.ClienteResponse;
import com.Tesis.Programacion.Service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public ResponseEntity<List<ClienteResponse>>getClientes(@RequestParam(required = false) Boolean activo, @RequestParam(required = false) String busqueda){
        return ResponseEntity.ok().body(clienteService.getClientes(activo, busqueda));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public ResponseEntity<ClienteResponse>agregarCliente(@Valid @RequestBody ClienteRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crearCliente(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public ResponseEntity<ClienteDetalleResponse>getClienteById(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.getClienteById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public ResponseEntity<ClienteDetalleResponse>actualizarCliente(@PathVariable Long id,
                                                                   @Valid @RequestBody ClienteRequest request){

        return ResponseEntity.ok().body(clienteService.actualizarCliente(request, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteDetalleResponse>darDeBajaCliente(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.bajaDeCliente(id));
    }

    @PatchMapping("/{id}/activar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteDetalleResponse>activarUsuario(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.activarCliente(id));
    }

    @GetMapping("/total")
    public ResponseEntity<Long>countCliente(){
        return ResponseEntity.ok(clienteService.countClientes());
    }
}
