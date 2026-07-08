package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Service.ValidacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validaciones")
public class ValidacionesController {

    @Autowired
    private ValidacionesService validacionesService;

    @GetMapping("/email/{email}")
    public ResponseEntity<Boolean> existsEmail(@PathVariable String email){
        return ResponseEntity.ok(validacionesService.existeEmail(email));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Boolean>existsDni(@PathVariable Integer dni){
        return ResponseEntity.ok(validacionesService.existeDni(dni));
    }
}
