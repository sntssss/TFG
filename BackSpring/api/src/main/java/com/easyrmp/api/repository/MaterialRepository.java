package com.easyrmp.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrmp.api.model.Material;


public interface MaterialRepository extends JpaRepository<Material, Long>{

    public Optional<Material> findByNombre(String nombre);

}
