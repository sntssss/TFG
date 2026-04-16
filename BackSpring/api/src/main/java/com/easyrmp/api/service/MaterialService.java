package com.easyrmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easyrmp.api.model.Material;
import com.easyrmp.api.repository.MaterialRepository;

@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    public ResponseEntity<?> obtenerMateriales() {
        return ResponseEntity.ok(materialRepository.findAll());
    }

    public ResponseEntity<?> nuevoMaterial(Material nuevoMaterial) {
        
        if (materialRepository.findByNombre(nuevoMaterial.getNombre()).isPresent()){
            return new ResponseEntity<>("Ya existe un material con este nombre.", HttpStatus.CONFLICT);
        }

        Material guardado = materialRepository.save(nuevoMaterial);

        return new ResponseEntity<>(guardado, HttpStatus.OK);

    }

    public ResponseEntity<?> eliminarMaterial(Material material) {
        
        if (materialRepository.existsById(material.getId())){
            materialRepository.delete(material);
            return new ResponseEntity<>(material, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

}
