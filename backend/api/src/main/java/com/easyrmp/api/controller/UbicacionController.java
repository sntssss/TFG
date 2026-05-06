package com.easyrmp.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyrmp.api.model.Ubicacion;
import com.easyrmp.api.service.UbicacionService;

@RestController
@RequestMapping("/ubicaciones")
public class UbicacionController {

    @Autowired
    UbicacionService ubicacionService;

    @GetMapping()
    public ResponseEntity<?> obtenerUbicaciones() {
        return ubicacionService.obtenerUbicaciones();
    }

    @PostMapping("/add")
    public ResponseEntity<?> nuevaUbicacion(@RequestBody Ubicacion ubicacion) {
        return ubicacionService.nuevaUbicacion(ubicacion);
    }
    
    @PostMapping("/delete")
    public ResponseEntity<?> eliminarUbicacion(@RequestBody Ubicacion ubicacion) {
        return ubicacionService.eliminarUbicacion(ubicacion);
    }
    
}
