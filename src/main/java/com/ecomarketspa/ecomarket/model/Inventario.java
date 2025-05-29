package com.ecomarketspa.ecomarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inventario {
    private String codigo;
    private String nombre;
    private int cantidad;
    private String ubicacion;
}
