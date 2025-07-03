package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Pedido;
import com.ecomarketspa.ecomarket.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<Pedido> getAll() {
        return repository.findAll();
    }

    public Pedido getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    public boolean update(Long id, Pedido nuevo) {
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
