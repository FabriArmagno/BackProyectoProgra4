package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTORequest.Reparacion.CrearReparacionRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Ventas.CrearVentaRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.HistorialReparacion.HistorialReparacionResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Venta.VentaResponse;
import com.Tesis.Programacion.Model.Historial;
import com.Tesis.Programacion.Service.HistorialReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping()
    public ResponseEntity<Historial> saveVenta(@RequestBody CrearReparacionRequest reparacionRequest){
        return ResponseEntity.ok(reparacionService.saveHistorialReparacion(reparacionRequest));
    }
}
