package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTORequest.Reparacion.CambiarEstadoRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.HistorialReparacion.HistorialReparacionResponse;
import com.Tesis.Programacion.Service.HistorialReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial/reparaciones")
public class HistorialReparacionController {
    @Autowired
    private HistorialReparacionService reparacionService;

    @GetMapping
    public ResponseEntity<List<HistorialReparacionResponse>> getReparaciones(){
        return ResponseEntity.ok(reparacionService.getReparaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialReparacionResponse> getReparacionById(@PathVariable Long id){
        return ResponseEntity.ok(reparacionService.getReparacionByID(id));
    }

    @PatchMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ENCARGADOTALLER')")
    public ResponseEntity<Void>cambiarEstado(@PathVariable Long id, @RequestBody CambiarEstadoRequest request){
        reparacionService.cambiarEstado(id, request);
        return ResponseEntity.noContent().build();
    }

}
