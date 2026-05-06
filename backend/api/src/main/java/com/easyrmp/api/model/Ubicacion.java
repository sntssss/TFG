package com.easyrmp.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

//Clase modelo con sus propiedades y sus relaciones, para hacer el mapeo de entidad-objeto entre la base de datos y el servidor

@Entity
@Table(name="ubicaciones")
@Data
@NoArgsConstructor
public class Ubicacion {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy="ubicacion")
    @JsonIgnoreProperties({"ubicacion", "proveedor", "material"})
    private List<Hu> listaHu;

}
