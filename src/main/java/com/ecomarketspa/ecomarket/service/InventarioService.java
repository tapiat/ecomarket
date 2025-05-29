package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Inventario;
import com.ecomarketspa.ecomarket.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository repository;
    public List<Inventario> getAllInventario() {
        return repository.getAll();
    }

    public Inventario getInventarioByCodigo(String codigo) {
        return repository.getByCodigo(codigo);
    }

    public Inventario addInventory(Inventario inventario) {
        return repository.save(inventario);
    }

    public boolean updateInventory(String codigo, Inventario inventario) {
        return repository.update(codigo, inventario);
    }

    public boolean deleteInventory(String codigo) {
        return repository.delete(codigo);
    }
}
