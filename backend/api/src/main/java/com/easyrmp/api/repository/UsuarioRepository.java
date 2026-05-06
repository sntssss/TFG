package com.easyrmp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrmp.api.model.Usuario;
//Interfaz que se encarga de conectar y mapear los objetos con la base de datos
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
