package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Venta.VentaResponse;
import com.Tesis.Programacion.Service.HistorialVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;// <-- Importante
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial/ventas")
public class HistorialVentaController {

    @Autowired
    private HistorialVentaService ventaService;

    // Pasamos el objeto Authentication al servicio
    @GetMapping
    public ResponseEntity<List<VentaResponse>> getVentas(Authentication authentication){
        return ResponseEntity.ok(ventaService.getVentas(authentication));
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
