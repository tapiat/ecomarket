package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Rol;
import com.ecomarketspa.ecomarket.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    public Rol getRolById(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public boolean updateRol(Long id, Rol rolActualizado) {
        if (rolRepository.existsById(id)) {
            rolActualizado.setId(id);
            rolRepository.save(rolActualizado);
            return true;
        }
        return false;
    }

    public boolean deleteRol(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

