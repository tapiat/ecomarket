package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Inventario;
import com.ecomarketspa.ecomarket.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> getAllInventario() {
        return inventarioRepository.findAll();
    }

    public Inventario getInventarioByCodigo(String codigo) {
        return inventarioRepository.findById(codigo).orElse(null);
    }

    public Inventario addInventory(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public boolean updateInventory(String codigo, Inventario nuevoInventario) {
        if (inventarioRepository.existsById(codigo)) {
            nuevoInventario.setCodigo(codigo);
            inventarioRepository.save(nuevoInventario);
            return true;
        }
        return false;
    }

    public boolean deleteInventory(String codigo) {
        if (inventarioRepository.existsById(codigo)) {
            inventarioRepository.deleteById(codigo);
            return true;
        }
        return false;
    }
}
