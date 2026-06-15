package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTORequest.Ventas.CrearVentaRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Venta.VentaResponse;
import com.Tesis.Programacion.Model.Historial;
import com.Tesis.Programacion.Service.HistorialVentaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial/ventas")
public class HistorialVentaController {

    @Autowired
    private HistorialVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaResponse>> getVentas(){
        return ResponseEntity.ok(ventaService.getVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> getVentaById(@PathVariable Long id){
        return ResponseEntity.ok(ventaService.getVentaById(id));
    }

    @PostMapping
    public ResponseEntity<VentaResponse> saveVenta(@RequestBody CrearVentaRequest venta){
        return ResponseEntity.ok(ventaService.createHistorial(venta));
    }







}
