package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Producto;
import com.ecomarketspa.ecomarket.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProducts() {
        return productoRepository.findAll();
    }

    public Producto getProductByCodigo(String codigo) {
        return productoRepository.findById(codigo).orElse(null);
    }

    public Producto addProduct(Producto producto) {
        return productoRepository.save(producto);
    }

    public boolean updateProduct(String codigo, Producto producto) {
        if (productoRepository.existsById(codigo)) {
            producto.setCodigo(codigo); // Aseguramos que el ID sea el mismo
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    public boolean deleteProduct(String codigo) {
        if (productoRepository.existsById(codigo)) {
            productoRepository.deleteById(codigo);
            return true;
        }
        return false;
    }
}
