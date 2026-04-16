package com.easyrmp.api.model;

import java.util.Date;

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

@Entity
@Table(name="hus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hu {

    @Id
    private Long sscc;

    private Long lote;

    private double peso;

    private Date fecha_caducidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion")
    @JsonBackReference("ref-ubicacion")
    private Ubicacion ubicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor")
    @JsonBackReference("ref-proveedor")
    private Proveedor proveedor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material")
    @JsonBackReference("ref-material")
    private Material material;

}
