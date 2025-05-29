package com.ecomarketspa.ecomarket.repository;

import com.ecomarketspa.ecomarket.model.Inventario;
import com.ecomarketspa.ecomarket.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InventarioRepository {
    private List<Inventario> listaInventario;

    public InventarioRepository() {
        listaInventario = new ArrayList<>();
        listaInventario.add(new Inventario("I001","Tierra de Hoja",100,"Bodega"));
    }

    public List<Inventario> getAll() {
        return listaInventario;
    }

    public Inventario getByCodigo(String codigo) {
        for (Inventario i : listaInventario) {
            if (i.getCodigo().equals(codigo)) {
                return i;
            }
        }
        return null;
    }

    public Inventario save(Inventario inventario) {
        listaInventario.add(inventario);
        return inventario;
    }

    public boolean update(String codigo, Inventario nuevoInventario) {
        for (Inventario i : listaInventario) {
            if (i.getCodigo().equals(codigo)) {
                i.setNombre(nuevoInventario.getNombre());
                i.setCantidad(nuevoInventario.getCantidad());
                i.setUbicacion(nuevoInventario.getUbicacion());
                return true;
            }
        }
        return false;
    }

    public boolean delete(String codigo) {
        for (Inventario i : listaInventario) {
            if (i.getCodigo().equals(codigo)) {
                listaInventario.remove(i);
                return true;
            }
        }
        return false;
    }
}
