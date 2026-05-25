package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Service.HistorialVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historial/reparaciones")
public class HistorialReparacionController {
    @Autowired
    private HistorialVentaService historialService;




}
