package com.ecomarketspa.ecomarket.service;


import com.ecomarketspa.ecomarket.model.DetallePedido;
import com.ecomarketspa.ecomarket.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository repository;

    public List<DetallePedido> getAll() {
        return repository.findAll();
    }

    public DetallePedido getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public DetallePedido save(DetallePedido detalle) {
        return repository.save(detalle);
    }

    public boolean update(Long id, DetallePedido nuevo) {
        if (repository.existsById(id)) {
            nuevo.setId(id);
            repository.save(nuevo);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
