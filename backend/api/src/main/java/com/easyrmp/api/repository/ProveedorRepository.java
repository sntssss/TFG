package com.easyrmp.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrmp.api.model.Proveedor;
//Interfaz que se encarga de conectar y mapear los objetos con la base de datos
public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{

    //Metodo para buscar proveedor por nombre, generado automaticamente por Springboot
    public Optional<Proveedor> findByNombre(String nombre);
}
