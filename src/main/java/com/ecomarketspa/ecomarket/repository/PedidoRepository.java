package com.ecomarketspa.ecomarket.repository;

import com.ecomarketspa.ecomarket.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

