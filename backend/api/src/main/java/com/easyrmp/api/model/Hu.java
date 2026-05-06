package com.easyrmp.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

//Clase modelo con sus propiedades y sus relaciones, para hacer el mapeo de entidad-objeto entre la base de datos y el servidor

@Entity
@Table(name="hus")
@Data
@NoArgsConstructor
public class Hu {

    @Id
    private Long sscc;

    private Long lote;

    private double peso;

    private Date fecha_caducidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion")
    @JsonIgnoreProperties("listaHu")
    private Ubicacion ubicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor")
    @JsonIgnoreProperties("listaHu")
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material")
    @JsonIgnoreProperties("listaHu")
    private Material material;

}
