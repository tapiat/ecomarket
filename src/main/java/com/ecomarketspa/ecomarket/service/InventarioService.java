package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.exception.DuplicateResourceException;
import com.ecomarketspa.ecomarket.exception.ResourceNotFoundException;
import com.ecomarketspa.ecomarket.model.Inventario;
import com.ecomarketspa.ecomarket.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    private final InventarioRepository repository;

    public InventarioService(InventarioRepository repository) {
        this.repository = repository;
    }

    public List<Inventario> getAll() {
        return repository.findAll();
    }

    public Inventario getByCodigo(String codigo) {
        return repository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con código: " + codigo));
    }

    public Inventario create(Inventario inventario) {
        if (repository.existsById(inventario.getCodigo())) {
            throw new DuplicateResourceException("Ya existe un registro con código: " + inventario.getCodigo());
        }
        return repository.save(inventario);
    }

    public Inventario update(String codigo, Inventario datos) {
        Inventario inventario = getByCodigo(codigo);
        inventario.setNombre(datos.getNombre());
        inventario.setCantidad(datos.getCantidad());
        inventario.setUbicacion(datos.getUbicacion());
        return repository.save(inventario);
    }

    public void delete(String codigo) {
        if (!repository.existsById(codigo)) {
            throw new ResourceNotFoundException("Inventario no encontrado con código: " + codigo);
        }
        repository.deleteById(codigo);
    }
}
