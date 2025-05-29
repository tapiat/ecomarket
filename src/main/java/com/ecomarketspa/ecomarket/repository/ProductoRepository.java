package com.ecomarketspa.ecomarket.repository;

import com.ecomarketspa.ecomarket.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepository {

    private List<Producto> listaProductos;

    public ProductoRepository() {
        listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("P001","Tierra de Hoja",
                "sustrato natural perfecto para crear un césped vibrante y saludable."
                ,6290));
    }

    public List<Producto> getAll() {
        return listaProductos;
    }

    public Producto getByCodigo(String codigo) {
        for (Producto p : listaProductos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public Producto save(Producto producto) {
        listaProductos.add(producto);
        return producto;
    }

    public boolean update(String codigo, Producto productoNuevo) {
        for (Producto p : listaProductos) {
            if (p.getCodigo().equals(codigo)) {
                p.setNombre(productoNuevo.getNombre());
                p.setDescripcion(productoNuevo.getDescripcion());
                p.setPrecio(productoNuevo.getPrecio());
                return true;
            }
        }
        return false;
    }

    public boolean delete(String codigo) {
        for (Producto p : listaProductos) {
            if (p.getCodigo().equals(codigo)) {
                listaProductos.remove(p);
                return true;
            }
        }
        return false;
    }
}
