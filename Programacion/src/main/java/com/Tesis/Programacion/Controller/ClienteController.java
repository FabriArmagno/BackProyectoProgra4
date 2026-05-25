package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTORequest.Cliente.CrearClienteRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Cliente.UpdateClienteRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente.ClienteDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente.ClienteResponse;
import com.Tesis.Programacion.Service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>>getClientes(@RequestParam(required = false) Boolean activo){
        if(activo==null){
            return ResponseEntity.ok().body(clienteService.getClientes());
        }

        return ResponseEntity.ok().body(clienteService.getClientesPorEstado(activo));
    }

    @PostMapping
    public ResponseEntity<ClienteDetalleResponse>agregarCliente(@Valid @RequestBody CrearClienteRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crearCliente(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDetalleResponse>getClienteById(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.getClienteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDetalleResponse>actualizarCliente(@PathVariable Long id,
                                                                   @Valid @RequestBody UpdateClienteRequest request){

        return ResponseEntity.ok().body(clienteService.actualizarCliente(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDetalleResponse>darDeBajaCliente(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.bajaDeCliente(id));
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<ClienteDetalleResponse>activarUsuario(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.activarCliente(id));
    }
}
