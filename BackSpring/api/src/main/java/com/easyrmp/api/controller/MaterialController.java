package com.easyrmp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyrmp.api.model.Material;
import com.easyrmp.api.service.MaterialService;



@RestController
@RequestMapping("/materiales")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @GetMapping()
    public ResponseEntity<?> obtenerMateriales() {
        return materialService.obtenerMateriales();
    }

    @PostMapping("/add")
    public ResponseEntity<?> nuevoMaterial(@RequestBody Material nuevoMaterial) {
        return materialService.nuevoMaterial(nuevoMaterial);
    }
    
    @PostMapping("/delete")
    public ResponseEntity<?> eliminarMaterial(@RequestBody Material material) {
        return materialService.eliminarMaterial(material);
    }
    

}
