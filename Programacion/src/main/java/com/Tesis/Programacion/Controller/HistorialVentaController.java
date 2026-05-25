package com.Tesis.Programacion.Controller;

import com.Tesis.Programacion.Service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historial/ventas")
public class HistorialVentaController {

    @Autowired
    private HistorialService historialService;





}
