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

@RestController
@RequestMapping("/hus")
public class HuController {

    @Autowired
    HuService huService;

    @GetMapping()
    public ResponseEntity<?> obtenerHus() {
        return huService.obtenerHus();
    }

    @GetMapping("/{sscc}")
    public ResponseEntity<?> usuarioPorSSCC(@PathVariable Long sscc) {
        return huService.usuarioPorSSCC(sscc);
    }
    

    @PostMapping("/add")
    public ResponseEntity<?> addHu(@RequestBody Hu nuevaHu) {
        return huService.addHu(nuevaHu);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> eliminarHu(@RequestBody Hu hu) {
        return huService.eliminarHu(hu);
    }
    
    @PutMapping("/edit/{sscc}")
    public ResponseEntity<?> editarUsuario(@PathVariable Long sscc, @RequestBody Hu hu) {
        return huService.editarHu(sscc, hu);
    }
}
