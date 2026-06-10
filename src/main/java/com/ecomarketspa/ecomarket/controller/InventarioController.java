package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Inventario;
import com.ecomarketspa.ecomarket.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> obtenerTodos() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Inventario> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(service.getByCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<Inventario> crear(@Valid @RequestBody Inventario inventario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(inventario));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Inventario> actualizar(@PathVariable String codigo,
                                                  @Valid @RequestBody Inventario inventario) {
        return ResponseEntity.ok(service.update(codigo, inventario));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        service.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}
