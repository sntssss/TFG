package com.easyrmp.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easyrmp.api.model.Usuario;
import com.easyrmp.api.repository.UsuarioRepository;

//Clase service donde se gestiona la logica de negocio
@Service
public class LoginService {

    @Autowired
    UsuarioRepository usuarioRepository;

    //Autentica un usuario, lo busca segun su dni y si existe comprueba las contraseñas, si lo autentica devuelve la información del usuario
    public ResponseEntity<?> autenticarUsuario(Usuario usuario) {
        
        Optional<Usuario> usuOptional = usuarioRepository.findById(usuario.getDni());

        if (usuOptional.isPresent()){
            if (usuOptional.get().getPassword().equals(usuario.getPassword())){
                return ResponseEntity.ok(usuOptional);
            }else{
                return new ResponseEntity<>("Error al autenticar el usuario.", HttpStatus.FORBIDDEN);
            }
        }else{
            return new ResponseEntity<>("No existe ningun usuario con el DNI " + usuario.getDni(), HttpStatus.FORBIDDEN);
        }
    }
}
