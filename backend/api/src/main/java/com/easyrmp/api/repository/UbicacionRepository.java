package com.easyrmp.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrmp.api.model.Ubicacion;
//Interfaz que se encarga de conectar y mapear los objetos con la base de datos
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long>{

    //Metodo para buscar ubicacion por nombre, generado automaticamente por Springboot
    public Optional<Ubicacion> findByNombre(String nombre);
}
