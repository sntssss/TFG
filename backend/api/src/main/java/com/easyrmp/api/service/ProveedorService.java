package com.easyrmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easyrmp.api.model.Proveedor;
import com.easyrmp.api.repository.ProveedorRepository;

//Clase service donde se gestiona la logica de negocio
@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    //Obtiene la lista de los proveedores existentes
    public ResponseEntity<?> obtenerProveedores() {
        return ResponseEntity.ok(proveedorRepository.findAll());
    }

    //Añade un nuevo proveedor, antes comprueba si existe ya el proveedor con dicho nombre
    public ResponseEntity<?> nuevoProveedor(Proveedor proveedor) {
        
        if (proveedorRepository.findByNombre(proveedor.getNombre()).isPresent()){
            return new ResponseEntity<>("Ya existe un proveedor con este nombre.", HttpStatus.CONFLICT);
        }

        Proveedor guardado = proveedorRepository.save(proveedor);

        return new ResponseEntity<>("Proveedor guardado: " + guardado, HttpStatus.OK);

    }

    //Elimina un proveedor, lo filtra por su nombre y si existe lo elimina
    public ResponseEntity<?> eliminarProveedor(Proveedor proveedor) {
        
        if (proveedorRepository.findByNombre(proveedor.getNombre()).isPresent()){
            proveedorRepository.delete(proveedor);
            return new ResponseEntity<>("Proveedor eliminado: " + proveedor, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Proveedor no encontrado.",HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
