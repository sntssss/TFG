package com.easyrmp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyrmp.api.model.Usuario;
import com.easyrmp.api.service.LoginService;

//Controlador del login con sus endpoints.

@RestController
@RequestMapping("/login")
public class LoginController {
    
    //Inyectamos el service para gestionar la logica de negocio
    @Autowired
    private LoginService loginService;

    //Endpoint que llama al metodo del service que gestiona las peticiones

    @PostMapping("/")
    public ResponseEntity<?> autenticarUsuario(@RequestBody Usuario usuario) {

        return loginService.autenticarUsuario(usuario);

    }
    

}
