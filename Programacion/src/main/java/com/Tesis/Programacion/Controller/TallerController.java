package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTORequest.Taller.AsignarTallerRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Taller.CrearTallerRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerEspecialidadResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerResponse;
import com.Tesis.Programacion.Service.TallerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taller")
public class TallerController {

    @Autowired
    private TallerService tallerService;

    @GetMapping
    public ResponseEntity<List<TallerResponse>>getTalleres(@RequestParam(required = false) Boolean activo){
        return ResponseEntity.ok(tallerService.getTalleresPorEstado(activo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TallerDetalleResponse>getTallerById(@PathVariable Long id){
        return ResponseEntity.ok(tallerService.getTallerByID(id));
    }

    @PostMapping
    public ResponseEntity<TallerDetalleResponse>crearTaller(@Valid @RequestBody CrearTallerRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(tallerService.createTaller(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteTaller(@PathVariable Long id){
        return ResponseEntity.ok(tallerService.deleteTaller(id));
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<TallerDetalleResponse>activarTaller(@PathVariable Long id){
        return ResponseEntity.ok(tallerService.activarTaller(id));
    }

    @GetMapping("/especialidades")
    public ResponseEntity<List<TallerEspecialidadResponse>>obtenerEspecialidad(){
        return ResponseEntity.ok(tallerService.obtenerEspecialidades());
    }

    @PostMapping("/asignar-vehiculo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>asignarVehiculo(@Valid @RequestBody AsignarTallerRequest request){
        tallerService.asignarVehiculoATaller(request);
        return ResponseEntity.ok().build();
    }

}
