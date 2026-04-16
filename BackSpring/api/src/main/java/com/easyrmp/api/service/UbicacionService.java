package com.easyrmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easyrmp.api.model.Ubicacion;
import com.easyrmp.api.repository.UbicacionRepository;

@Service
public class UbicacionService {

    @Autowired
    UbicacionRepository ubicacionRepository;

    public ResponseEntity<?> obtenerUbicaciones() {
        return ResponseEntity.ok(ubicacionRepository.findAll());
    }

    public ResponseEntity<?> nuevaUbicacion(Ubicacion ubicacion) {
        
        if (ubicacionRepository.findByNombre(ubicacion.getNombre()).isPresent()){
            return new ResponseEntity<>("Ya existe un ubicacion con este nombre.", HttpStatus.CONFLICT);
        }

        Ubicacion guardado = ubicacionRepository.save(ubicacion);

        return new ResponseEntity<>("Ubicacion guardada: " + guardado, HttpStatus.OK);

    }

    public ResponseEntity<?> eliminarUbicacion(Ubicacion ubicacion) {
        
        if (ubicacionRepository.findByNombre(ubicacion.getNombre()).isPresent()){
            ubicacionRepository.delete(ubicacion);
            return new ResponseEntity<>("Ubicacion eliminado: " + ubicacion, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Ubicacion no encontrado.",HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
