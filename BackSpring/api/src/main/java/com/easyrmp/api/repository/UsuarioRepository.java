package com.easyrmp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrmp.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
