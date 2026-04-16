package com.easyrmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easyrmp.api.model.Usuario;
import com.easyrmp.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuariosRepository;

    public ResponseEntity<?> obtenerUsuarios(){
        return ResponseEntity.ok(usuariosRepository.findAll());
    }

    public ResponseEntity<?> usuarioPorDNI(String dni){
        
        Usuario usuario = usuariosRepository.findById(dni).orElse(null);
        if (usuario != null){
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> anyadirUsuario(Usuario nuevoUsuario){

        if (usuariosRepository.existsById(nuevoUsuario.getDni())){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            usuariosRepository.save(nuevoUsuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> eliminarUsuario(Usuario usuario){
        
        if (usuariosRepository.existsById(usuario.getDni())){
            usuariosRepository.delete(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    public ResponseEntity<?> editarUsuario(String dni, Usuario usuario){
        
        if (usuariosRepository.existsById(dni)){
            usuariosRepository.save(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
