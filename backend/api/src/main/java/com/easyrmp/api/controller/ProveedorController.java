package com.easyrmp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyrmp.api.model.Proveedor;
import com.easyrmp.api.service.ProveedorService;

//Controlador de los proveedores con sus endpoints.

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    //Inyectamos el service para gestionar la logica de negocio
    @Autowired
    ProveedorService proveedorService;

    //Endpoints que llaman a los metodos del service que gestionan las peticiones

    @GetMapping()
    public ResponseEntity<?> obtenerProveedores() {
        return proveedorService.obtenerProveedores();
    }

    @PostMapping("/add")
    public ResponseEntity<?> nuevoProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.nuevoProveedor(proveedor);
    }
    
    @PostMapping("/delete")
    public ResponseEntity<?> eliminarProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.eliminarProveedor(proveedor);
    }
    

}
