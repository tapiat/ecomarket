package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Producto;
import com.ecomarketspa.ecomarket.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository repository;
    public List<Producto> getProducts() {
        return repository.getAll();
    }

    public Producto getProductByCodigo(String codigo) {
        return repository.getByCodigo(codigo);
    }

    public Producto addProduct(Producto producto) {
        return repository.save(producto);
    }

    public boolean updateProduct(String codigo, Producto producto) {
        return repository.update(codigo, producto);
    }

    public boolean deleteProduct(String codigo) {
        return repository.delete(codigo);
    }
}
