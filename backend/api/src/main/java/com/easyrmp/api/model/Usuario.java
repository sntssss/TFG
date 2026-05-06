package com.easyrmp.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Clase modelo con sus propiedades y sus relaciones, para hacer el mapeo de entidad-objeto entre la base de datos y el servidor

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private String dni;

    private String password;

    private String nombre;
    
    private String apellidos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol")
    @JsonBackReference
    private Rol rol;
}
