package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.exception.DuplicateResourceException;
import com.ecomarketspa.ecomarket.exception.ResourceNotFoundException;
import com.ecomarketspa.ecomarket.model.Producto;
import com.ecomarketspa.ecomarket.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> getAll() {
        return repository.findAll();
    }

    public Producto getByCodigo(String codigo) {
        return repository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con código: " + codigo));
    }

    public Producto create(Producto producto) {
        if (repository.existsById(producto.getCodigo())) {
            throw new DuplicateResourceException("Ya existe un producto con código: " + producto.getCodigo());
        }
        return repository.save(producto);
    }

    public Producto update(String codigo, Producto datos) {
        Producto producto = getByCodigo(codigo);
        producto.setNombre(datos.getNombre());
        producto.setDescripcion(datos.getDescripcion());
        producto.setPrecio(datos.getPrecio());
        return repository.save(producto);
    }

    public void delete(String codigo) {
        if (!repository.existsById(codigo)) {
            throw new ResourceNotFoundException("Producto no encontrado con código: " + codigo);
        }
        repository.deleteById(codigo);
    }
}
