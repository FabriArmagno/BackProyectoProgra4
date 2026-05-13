package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.Auto;
import com.Tesis.Programacion.Model.DTO.CrearVehiculoDTO;
import com.Tesis.Programacion.Model.DTO.SubModelDTO;
import com.Tesis.Programacion.Model.DTO.VehiculoDetalleDTO;
import com.Tesis.Programacion.Model.Vehiculo;
import com.Tesis.Programacion.Service.CarApiService;
import com.Tesis.Programacion.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/auto/agregar")
    public ResponseEntity<Vehiculo> agregarAuto(@RequestBody CrearVehiculoDTO crearVehiculoDTO){
        VehiculoDetalleDTO vehiculoDetalleDTO = carApiService.obtenerDetalleDelVehiculo(crearVehiculoDTO.getIdTrim());

        Auto auto=new Auto();
        auto.setPatente(crearVehiculoDTO.getPatente());
        auto.setMarca(vehiculoDetalleDTO.getMake());
        auto.setModelo(vehiculoDetalleDTO.getModel());
        auto.setPrecio(crearVehiculoDTO.getPrecio());
        auto.setColor(crearVehiculoDTO.getColor());
        auto.setAño(vehiculoDetalleDTO.getYear());
        auto.setKilometraje(crearVehiculoDTO.getKilometraje());
        auto.setSubmodelo(vehiculoDetalleDTO.getDescription());
        auto.setFechaIngreso(LocalDate.now());
        auto.setEnReparacion(false);
        auto.setVendido(false);

        if(!vehiculoDetalleDTO.getEngines().isEmpty()){
            auto.setMotor(vehiculoDetalleDTO.getEngines().getFirst().getSize());
            auto.setCombustion(vehiculoDetalleDTO.getEngines().getFirst().getEngine_type());
            auto.setPotencia(vehiculoDetalleDTO.getEngines().getFirst().getHorsepower_hp());
        }

        if(!vehiculoDetalleDTO.getDrive_types().isEmpty()){
            auto.setTipoDeTraccion(vehiculoDetalleDTO.getDrive_types().getFirst().getDescription());
        }

        if(!vehiculoDetalleDTO.getTransmissions().isEmpty()){
            auto.setTransmision(vehiculoDetalleDTO.getTransmissions().getFirst().getDescription());
        }

        if(!vehiculoDetalleDTO.getBodies().isEmpty()){
            auto.setPuertas(vehiculoDetalleDTO.getBodies().getFirst().getDoors());
            auto.setTipoAuto(vehiculoDetalleDTO.getBodies().getFirst().getType());
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.createVehiculo(auto));
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
    public List<String>getModelos(@RequestParam String tipo, @RequestParam String make){
        return carApiService.obtenerModelos(tipo, make);
    }

    @GetMapping("/anios")
    public List<Integer>getAnios(@RequestParam String tipo, @RequestParam String model){
        return carApiService.obtenerAnios(tipo, model);
    }

    @GetMapping("/submodelos")
    public List<SubModelDTO>getSubmodelos(@RequestParam String model, @RequestParam Integer year){
        return carApiService.obtenerSubmodels(model, year);
    }



}
