package com.easyrmp.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrmp.api.model.Material;

//Interfaz que se encarga de conectar y mapear los objetos con la base de datos
public interface MaterialRepository extends JpaRepository<Material, Long>{

    public Optional<Material> findByNombre(String nombre);

}
