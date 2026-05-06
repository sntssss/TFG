package com.easyrmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easyrmp.api.model.Usuario;
import com.easyrmp.api.repository.UsuarioRepository;

//Clase service donde se gestiona la logica de negocio
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuariosRepository;

    //Obtiene la lista de usuarios existente
    public ResponseEntity<?> obtenerUsuarios(){
        return ResponseEntity.ok(usuariosRepository.findAll());
    }

    //Busca un usuario por su identificador (DNI)
    public ResponseEntity<?> usuarioPorDNI(String dni){
        
        Usuario usuario = usuariosRepository.findById(dni).orElse(null);
        if (usuario != null){
            return new ResponseEntity<>("Usuario encontrado: " + usuario, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Usuario no encontrado: " + usuario, HttpStatus.NOT_FOUND);
        }
    }

    //Recibe y trata de añadir el usuario, antes comprueba que no exista ese identificador en la base de datos
    public ResponseEntity<?> anyadirUsuario(Usuario nuevoUsuario){

        if (usuariosRepository.existsById(nuevoUsuario.getDni())){
            return new ResponseEntity<>("El DNI ya esta en uso: " + nuevoUsuario.getDni(),HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            usuariosRepository.save(nuevoUsuario);
            return new ResponseEntity<>("Usuario guardado: " + nuevoUsuario, HttpStatus.OK);
        }
    }

    //Elimina un usuario segun su DNI, comprueba antes que exista
    public ResponseEntity<?> eliminarUsuario(String dni){
        
        Usuario usuario = usuariosRepository.findById(dni).orElse(null);

        if (usuario != null){
            usuariosRepository.delete(usuario);
            return new ResponseEntity<>("Usuario eliminado: " + usuario, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No se ha encontrado ningun usuario con DNI " + dni,HttpStatus.NOT_ACCEPTABLE);
        }

    }

    //Edita un usuario, comprueba que dicho usuario existe con su DNI y lo modifica si asi es.
    public ResponseEntity<?> editarUsuario(String dni, Usuario usuarioEditado) {

        if (!usuariosRepository.existsById(dni)) {
            return new ResponseEntity<>("No se ha encontrado el usuario con DNI " + dni, HttpStatus.NOT_FOUND);
        }

        // Si el DNI ha cambiado, eliminamos el registro viejo
        if (!dni.equals(usuarioEditado.getDni())) {
            usuariosRepository.deleteById(dni);
        }

        usuariosRepository.save(usuarioEditado);
        return new ResponseEntity<>("Usuario editado correctamente: " + usuarioEditado, HttpStatus.OK);
    }
}
