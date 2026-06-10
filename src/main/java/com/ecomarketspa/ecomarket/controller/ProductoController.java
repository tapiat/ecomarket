package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Producto;
import com.ecomarketspa.ecomarket.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Producto> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(service.getByCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@Valid @RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(producto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Producto> actualizar(@PathVariable String codigo,
                                                @Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(service.update(codigo, producto));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        service.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}
