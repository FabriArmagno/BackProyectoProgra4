package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Venta.VentaResponse;
import com.Tesis.Programacion.Service.HistorialVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;// <-- Importante
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class HistorialVentaController {

    @Autowired
    private HistorialVentaService ventaService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<VentaResponse>> getVentas(
            @RequestParam(required = false) Long vendedorId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {

        return ResponseEntity.ok(ventaService.getVentas(vendedorId, desde, hasta));
    }

    @GetMapping("/mis-ventas")
    @PreAuthorize("hasRole('EMPLEADO')")
    public ResponseEntity<List<VentaResponse>>getVentasPorEmpleado(Authentication authentication){
        return ResponseEntity.ok(ventaService.getVentasPorEmpleado(authentication));
    }

    @GetMapping("/facturacion-mes")
    public ResponseEntity<Double> getFacturacionDelMes() {
        return ResponseEntity.ok(ventaService.facturacionDelMes());
    }

    @GetMapping("/ventas-mes")
    public ResponseEntity<Long> getVentasDelMes() {
        return ResponseEntity.ok(ventaService.ventasDelMes());
    }

    @GetMapping("/empleado/ventas-mes")
    public ResponseEntity<Long> getVentasDelMesEmpleado(Authentication authentication) {
        return ResponseEntity.ok(ventaService.ventasDelMesPorEmpleado(authentication));
    }

    @GetMapping("/empleado/facturacion-mes")
    public ResponseEntity<Double> getFacturacionDelMesEmpleado(Authentication authentication) {
        return ResponseEntity.ok(ventaService.facturacionDelMesPorEmpleado(authentication));
    }

    @GetMapping("/empleado/ultimas-ventas")
    public ResponseEntity<List<VentaResponse>> getUltimasTresVentasEmpleado(Authentication authentication) {
        return ResponseEntity.ok(ventaService.obtenerUltimasTresVentas(authentication));
    }
}
