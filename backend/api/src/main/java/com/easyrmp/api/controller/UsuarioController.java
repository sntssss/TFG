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

import com.easyrmp.api.model.Usuario;
import com.easyrmp.api.service.UsuarioService;

//Controlador de los usuarios con sus endpoints.

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    //Inyectamos el service para gestionar la logica de negocio
    @Autowired
    UsuarioService usuariosService;

    //Endpoints que llaman a los metodos del service que gestionan las peticiones

    @GetMapping()
    public ResponseEntity<?> obtenerUsuarios() {
        return usuariosService.obtenerUsuarios();
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> usuarioPorDNI(@PathVariable String dni) {
        return usuariosService.usuarioPorDNI(dni);
    }
    

    @PostMapping("/add")
    public ResponseEntity<?> anyadirUsuario(@RequestBody Usuario nuevoUsuario) {
        return usuariosService.anyadirUsuario(nuevoUsuario);
    }

    @PostMapping("/delete/{dni}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable String dni) {
        return usuariosService.eliminarUsuario(dni);
    }
    
    @PutMapping("/edit/{dni}")
    public ResponseEntity<?> editarUsuario(@PathVariable String dni, @RequestBody Usuario usuario) {
        return usuariosService.editarUsuario(dni, usuario);
    }
}