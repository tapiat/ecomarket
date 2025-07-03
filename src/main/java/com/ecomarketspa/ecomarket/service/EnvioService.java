package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Envio;
import com.ecomarketspa.ecomarket.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository repository;

    public List<Envio> getAll() {
        return repository.findAll();
    }

    public Envio getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Envio save(Envio envio) {
        return repository.save(envio);
    }

    public boolean update(Long id, Envio nuevo) {
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
