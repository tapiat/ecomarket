package com.ecomarketspa.ecomarket.repository;

import com.ecomarketspa.ecomarket.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, String> {
}
