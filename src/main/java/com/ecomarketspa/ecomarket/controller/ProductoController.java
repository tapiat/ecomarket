package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Producto;
import com.ecomarketspa.ecomarket.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        return ResponseEntity.ok(service.getProducts());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Producto> obtenerPorCodigo(@PathVariable String codigo) {
        Producto producto = service.getProductByCodigo(codigo);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        Producto creado = service.addProduct(producto);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<String> actualizarProducto(@PathVariable String codigo, @RequestBody Producto producto) {
        boolean actualizado = service.updateProduct(codigo, producto);
        if (actualizado) {
            return ResponseEntity.ok("Producto actualizado con éxito.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> eliminarProducto(@PathVariable String codigo) {
        boolean eliminado = service.deleteProduct(codigo);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
