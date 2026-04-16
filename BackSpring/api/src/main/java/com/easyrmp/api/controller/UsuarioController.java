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

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuariosService;

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

    @PostMapping("/delete")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario usuario) {
        return usuariosService.eliminarUsuario(usuario);
    }
    
    @PutMapping("/edit/{dni}")
    public ResponseEntity<?> editarUsuario(@PathVariable String dni, @RequestBody Usuario usuario) {
        return usuariosService.editarUsuario(dni, usuario);
    }
}