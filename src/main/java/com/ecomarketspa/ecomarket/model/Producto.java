package com.ecomarketspa.ecomarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Producto {

    @Id
    private String codigo;

    private String nombre;
    private String descripcion;
    private int precio;
}
