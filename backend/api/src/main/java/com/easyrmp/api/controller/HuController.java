package com.easyrmp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyrmp.api.model.Hu;
import com.easyrmp.api.service.HuService;

//Controlador de las HUS con sus endpoints.
@RestController
@RequestMapping("/hus")
public class HuController {

    //Inyectamos el service para gestionar la logica de negocio
    @Autowired
    HuService huService;

    //Endpoints que llaman a los metodos del service que gestionan las peticiones

    @GetMapping()
    public ResponseEntity<?> obtenerHus() {
        return huService.obtenerHus();
    }

    @GetMapping("/{sscc}")
    public ResponseEntity<?> huPorSSCC(@PathVariable Long sscc) {
        return huService.huPorSSCC(sscc);
    }
    

    @PostMapping("/add")
    public ResponseEntity<?> addHu(@RequestBody Hu nuevaHu) {
        return huService.addHu(nuevaHu);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> eliminarHu(@RequestBody Long sscc) {
        return huService.eliminarHu(sscc);
    }
    
    @PutMapping("/edit/{sscc}")
    public ResponseEntity<?> editarUsuario(@PathVariable Long sscc, @RequestBody Hu hu) {
        return huService.editarHu(sscc, hu);
    }
}
