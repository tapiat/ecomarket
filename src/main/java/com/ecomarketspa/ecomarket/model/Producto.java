package com.ecomarketspa.ecomarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Producto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private int precio;
}
