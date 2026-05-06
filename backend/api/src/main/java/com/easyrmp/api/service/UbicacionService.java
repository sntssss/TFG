package com.easyrmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easyrmp.api.model.Ubicacion;
import com.easyrmp.api.repository.UbicacionRepository;

//Clase service donde se gestiona la logica de negocio
@Service
public class UbicacionService {

    @Autowired
    UbicacionRepository ubicacionRepository;

    //obtiene todas las ubicaciones existentes
    public ResponseEntity<?> obtenerUbicaciones() {
        return ResponseEntity.ok(ubicacionRepository.findAll());
    }

    //Crea una nueva ubicacion, compreba antes si esta ubicacion ya existe
    public ResponseEntity<?> nuevaUbicacion(Ubicacion ubicacion) {
        
        if (ubicacionRepository.findByNombre(ubicacion.getNombre()).isPresent()){
            return new ResponseEntity<>("Ya existe un ubicacion con este nombre.", HttpStatus.CONFLICT);
        }

        Ubicacion guardado = ubicacionRepository.save(ubicacion);

        return new ResponseEntity<>("Ubicacion guardada: " + guardado, HttpStatus.OK);

    }

    //Elimina una ubicacion, la busca por su nombre y si existe la elimina
    public ResponseEntity<?> eliminarUbicacion(Ubicacion ubicacion) {
        
        if (ubicacionRepository.findByNombre(ubicacion.getNombre()).isPresent()){
            ubicacionRepository.delete(ubicacion);
            return new ResponseEntity<>("Ubicacion eliminado: " + ubicacion, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Ubicacion no encontrado.",HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
