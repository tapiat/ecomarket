package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Inventario;
import com.ecomarketspa.ecomarket.model.Producto;
import com.ecomarketspa.ecomarket.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService service;

    @GetMapping
    public ResponseEntity<List<Inventario>> getAllInventario() {
        return ResponseEntity.ok(service.getAllInventario());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable String codigo) {
        Inventario inventario = service.getInventarioByCodigo(codigo);
        if (inventario != null) {
            return ResponseEntity.ok(inventario);
        } else  {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Inventario> createInventario(@RequestBody Inventario inventario) {
        Inventario creador = service.addInventory(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creador);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable String codigo, @RequestBody Inventario inventario) {
        boolean actualizado = service.updateInventory(codigo, inventario);
        if (actualizado) {
            return ResponseEntity.ok(inventario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Inventario> deleteInventario(@PathVariable String codigo) {
        boolean eliminado = service.deleteInventory(codigo);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
