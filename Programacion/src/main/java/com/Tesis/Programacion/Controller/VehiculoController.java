package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.Vehiculo;
import com.Tesis.Programacion.Service.CarApiService;
import com.Tesis.Programacion.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private CarApiService carApiService;

    @GetMapping
    public ResponseEntity<List<Vehiculo>> getVehiculos(){
        return ResponseEntity.ok(vehiculoService.getVehiculos());
    }

    @PostMapping("/agregar")
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.createVehiculo(vehiculo));
    }

    @GetMapping("/list/patente/{patente}")
    public ResponseEntity<?> getVehiculoByPatente(@PathVariable String patente){
        return vehiculoService.getVehiculoByPatente(patente).
                map(vehiculo -> ResponseEntity.ok().body(vehiculo))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/list/marca/{marca}")
    public ResponseEntity<?> getVehiculoByMarca (@PathVariable String marca){
        return vehiculoService.getVehiculoByMarca(marca).
                map(vehiculo -> ResponseEntity.ok().body(vehiculo))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/marcas")
    public List<String>getMarcas(@RequestParam String tipo){
        return carApiService.obtenerMarcas(tipo);
    }

    @GetMapping("/modelos")
    public List<String>getModelos(@RequestParam String tipo, @RequestParam String marca){
        return carApiService.obtenerModelos(tipo, marca);
    }

    @GetMapping("/anios")
    public List<Integer>getAnios(@RequestParam String tipo, @RequestParam String modelo){
        return carApiService.obtenerAnios(tipo, modelo);
    }

}
