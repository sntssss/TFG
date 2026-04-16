package com.easyrmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easyrmp.api.model.Proveedor;
import com.easyrmp.api.repository.ProveedorRepository;

@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    public ResponseEntity<?> obtenerProveedores() {
        return ResponseEntity.ok(proveedorRepository.findAll());
    }

    public ResponseEntity<?> nuevoProveedor(Proveedor proveedor) {
        
        if (proveedorRepository.findByNombre(proveedor.getNombre()).isPresent()){
            return new ResponseEntity<>("Ya existe un proveedor con este nombre.", HttpStatus.CONFLICT);
        }

        Proveedor guardado = proveedorRepository.save(proveedor);

        return new ResponseEntity<>("Proveedor guardado: " + guardado, HttpStatus.OK);

    }

    public ResponseEntity<?> eliminarProveedor(Proveedor proveedor) {
        
        if (proveedorRepository.findByNombre(proveedor.getNombre()).isPresent()){
            proveedorRepository.delete(proveedor);
            return new ResponseEntity<>("Proveedor eliminado: " + proveedor, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Proveedor no encontrado.",HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
