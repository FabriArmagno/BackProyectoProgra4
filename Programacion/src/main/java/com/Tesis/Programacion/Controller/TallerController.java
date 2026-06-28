package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.DTO.DTORequest.Taller.AsignarTallerRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Taller.CrearTallerRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Enum.EnumResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerResponse;
import com.Tesis.Programacion.Service.TallerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taller")
public class TallerController {

    @Autowired
    private TallerService tallerService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TallerResponse>>getTalleres(@RequestParam(required = false) Boolean activo){
        return ResponseEntity.ok(tallerService.getTalleresPorEstado(activo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TallerDetalleResponse>getTallerById(Authentication auth, @PathVariable Long id){
        return ResponseEntity.ok(tallerService.getTallerByID(auth, id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TallerDetalleResponse>crearTaller(@Valid @RequestBody CrearTallerRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(tallerService.createTaller(request));
    }

    @GetMapping("/mis-talleres")
    @PreAuthorize("hasRole('ENCARGADOTALLER')")
    public ResponseEntity<List<TallerResponse>>getTalleresPorEncargado(Authentication authentication){
        String email=authentication.getName();
        return ResponseEntity.ok(tallerService.getTalleresPorEncargado(authentication));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>deleteTaller(@PathVariable Long id){
        tallerService.deleteTaller(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>activarTaller(@PathVariable Long id){
        tallerService.activarTaller(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/especialidades")
    public ResponseEntity<List<EnumResponse>>obtenerEspecialidad(){
        return ResponseEntity.ok(tallerService.obtenerEspecialidades());
    }

    @PostMapping("/asignar-vehiculo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>asignarVehiculo(@Valid @RequestBody AsignarTallerRequest request){
        tallerService.asignarVehiculoATaller(request);
        return ResponseEntity.noContent().build();
    }

}
