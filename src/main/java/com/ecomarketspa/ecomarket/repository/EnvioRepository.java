package com.ecomarketspa.ecomarket.repository;

import com.ecomarketspa.ecomarket.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
}
