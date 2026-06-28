package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Venta.VentaResponse;
import com.Tesis.Programacion.Service.HistorialVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;// <-- Importante
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class HistorialVentaController {

    @Autowired
    private HistorialVentaService ventaService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<VentaResponse>> getVentas(){
        return ResponseEntity.ok(ventaService.getVentas());
    }

    @GetMapping("/mis-ventas")
    @PreAuthorize("hasRole('EMPLEADO')")
    public ResponseEntity<List<VentaResponse>>getVentasPorEmpleado(Authentication authentication){
        return ResponseEntity.ok(ventaService.getVentasPorEmpleado(authentication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> getVentaById(@PathVariable Long id, Authentication authentication){
        return ResponseEntity.ok(ventaService.getVentaById(id, authentication));
    }

    @GetMapping("/cantidad")
    public ResponseEntity<Long> contarVentas(Authentication authentication){
        return ResponseEntity.ok(ventaService.contarVentas(authentication));
    }
}
