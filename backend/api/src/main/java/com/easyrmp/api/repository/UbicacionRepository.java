package com.easyrmp.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrmp.api.model.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long>{
    public Optional<Ubicacion> findByNombre(String nombre);
}
