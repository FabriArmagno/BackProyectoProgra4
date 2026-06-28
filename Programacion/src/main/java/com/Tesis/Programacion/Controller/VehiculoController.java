package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Auto.CrearAutoRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Auto.UpdateAutoRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Moto.CrearMotoRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Ventas.CrearVentaRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.Auto.AutoDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.CarApi.SubModelDTO;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.Moto.MotoDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Enum.EnumResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoResponse;
import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Service.AutoService;
import com.Tesis.Programacion.Service.CarApiService;
import com.Tesis.Programacion.Service.MotoService;
import com.Tesis.Programacion.Service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private AutoService autoService;

    @Autowired
    private MotoService motoService;

    @Autowired
    private CarApiService carApiService;

    ///------------------------------------------VEHICULOS----------------------------------------------------------

    @GetMapping
    public ResponseEntity<List<VehiculoResponse>> getVehiculosByEstado(@RequestParam(required = false) Estado estado) {
        return ResponseEntity.ok(vehiculoService.getVehiculoByEstado(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDetalleResponse> getDetalleVehiculo(@PathVariable Long id) {
        return ResponseEntity.ok().body(vehiculoService.getVehiculoById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>eliminarVehiculo(@PathVariable Long id){
        vehiculoService.eliminarVehiculo(id);
        return ResponseEntity.noContent().build();
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

    @PutMapping("/vender/{vehiculoId}")
    public ResponseEntity<Void> venderAuto(Authentication authentication, @Valid @RequestBody CrearVentaRequest request, @PathVariable Long vehiculoId){
        vehiculoService.venderAuto(authentication, request, vehiculoId);

        return ResponseEntity.ok().build();
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

    ///------------------------------------------AUTO---------------------------------------------------------------

    @PostMapping(value = "/autos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AutoDetalleResponse> agregarAuto(
            @RequestPart("datos") @Valid CrearAutoRequest crearAutoRequest,
        @RequestPart(value = "files", required = false) List<MultipartFile> files) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(autoService.createAuto(crearAutoRequest, files));
    }

    @PutMapping("/autos/{id}")
    public ResponseEntity<AutoDetalleResponse> modificarAuto(@RequestBody @Valid UpdateAutoRequest request, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(autoService.modificarAuto(id, request));
    }

    ///------------------------------------------MOTO----------------------------------------------------------------
    @PostMapping(value = "/motos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MotoDetalleResponse> agregarMoto(
            @RequestPart("datos") @Valid CrearMotoRequest crearMotoRequest,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(motoService.crearMoto(crearMotoRequest, files));
    }

    @PutMapping("/motos/{id}")
    public ResponseEntity<MotoDetalleResponse> editarMoto(
            @PathVariable Long id,
            @Valid @RequestBody CrearMotoRequest crearMotoRequest
    ) {
        MotoDetalleResponse motoActualizada = motoService.editarMoto(id, crearMotoRequest);

        return ResponseEntity.ok(motoActualizada);
    }


    @DeleteMapping("/{id}/imagenes")
    public ResponseEntity<String> eliminarImagenDeVehiculo(
            @PathVariable Long id,
            @RequestParam("nombre") String nombreImagen)
    {
        vehiculoService.eliminarImagen(id,nombreImagen);
        return ResponseEntity.ok("Imagen eliminada correctamente.");
    }


    @PostMapping(value = "/{id}/imagenes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> agregarImagenesAVehiculo(
            @PathVariable Long id,
            @RequestPart("files") List<MultipartFile> files) {

        vehiculoService.agregarImagenes(id, files);
        return ResponseEntity.ok("Imágenes agregadas correctamente.");
    }

    ///------------------------------------------ESTADOS-----------------------------------------------------------------

    @GetMapping("/estados")
    public ResponseEntity<List<EnumResponse>>getEstados(){
        return ResponseEntity.ok(vehiculoService.getEstados());
    }

    ///------------------------------------------TIPO MOTO---------------------------------------------------------------

    @GetMapping("/tipos-moto")
    public ResponseEntity<List<EnumResponse>>getTiposMoto(){
        return ResponseEntity.ok(motoService.obtenerTiposDeMotos());
    }
}
