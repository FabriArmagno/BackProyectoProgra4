package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTORequest.Auto.CrearAutoRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Auto.AutoDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Auto.AutoResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.CarApi.SubModelDTO;
import com.Tesis.Programacion.Service.AutoService;
import com.Tesis.Programacion.Service.CarApiService;
import com.Tesis.Programacion.Service.VehiculoService;
import jakarta.validation.Valid;
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
    private AutoService autoService;

    @Autowired
    private CarApiService carApiService;

    ///------------------------------------------AUTO---------------------------------------------------------------

    @GetMapping("/autos")
    public ResponseEntity<List<AutoResponse>> getAutos(){
        return ResponseEntity.ok().body(autoService.getAutos());
    }

    @GetMapping("/autos/{id}")
    public ResponseEntity<AutoDetalleResponse> getDetalleAuto(@PathVariable Long id) {
        return ResponseEntity.ok().body(autoService.getAutoById(id));
    }

   @PostMapping("/autos")
    public ResponseEntity<AutoDetalleResponse> agregarAuto(@Valid @RequestBody CrearAutoRequest crearAutoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(autoService.createAuto(crearAutoRequest));
    }

    @PutMapping("/autos/{id}")
    public ResponseEntity<AutoDetalleResponse> venderAuto(@PathVariable Long id){
        return ResponseEntity.ok().body(autoService.venderAuto(id));
    }

    ///-------------------------------AUTO Y MOTO-----------------------------------------------------------------

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
