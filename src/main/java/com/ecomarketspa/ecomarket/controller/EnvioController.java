package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Envio;
import com.ecomarketspa.ecomarket.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService service;

    @GetMapping
    public ResponseEntity<List<Envio>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> getById(@PathVariable Long id) {
        Envio envio = service.getById(id);
        return envio != null ? ResponseEntity.ok(envio) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Envio> create(@RequestBody Envio envio) {
        return ResponseEntity.status(201).body(service.save(envio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Envio envio) {
        return service.update(id, envio)
                ? ResponseEntity.ok("Envío actualizado.")
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
