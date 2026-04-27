package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Model.Historial;
import com.Tesis.Programacion.Service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historial")
public class HistorialController {
    @Autowired
    private HistorialService historialService;

    @GetMapping
    public ResponseEntity<List<Historial>>getHistoriales(){
        return ResponseEntity.ok(historialService.getHistoriales());
    }


}
