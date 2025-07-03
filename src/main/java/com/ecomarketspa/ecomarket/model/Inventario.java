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
public class Inventario {

    @Id
    private String codigo;

    private String nombre;
    private int cantidad;
    private String ubicacion;
}
