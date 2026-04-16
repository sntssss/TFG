package com.easyrmp.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrmp.api.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{

    public Optional<Proveedor> findByNombre(String nombre);
}
