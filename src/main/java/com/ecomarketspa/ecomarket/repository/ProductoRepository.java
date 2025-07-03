package com.ecomarketspa.ecomarket.repository;

import com.ecomarketspa.ecomarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
    // ya puedes usar: findAll(), findById(), save(), deleteById(), etc.
}

