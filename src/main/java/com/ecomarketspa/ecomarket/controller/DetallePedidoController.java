package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.DetallePedido;
import com.ecomarketspa.ecomarket.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService service;

    @GetMapping
    public ResponseEntity<List<DetallePedido>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> getById(@PathVariable Long id) {
        DetallePedido detalle = service.getById(id);
        return detalle != null ? ResponseEntity.ok(detalle) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DetallePedido> create(@RequestBody DetallePedido detalle) {
        return ResponseEntity.status(201).body(service.save(detalle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody DetallePedido detalle) {
        return service.update(id, detalle)
                ? ResponseEntity.ok("Detalle actualizado.")
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
