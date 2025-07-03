package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Pedido;
import com.ecomarketspa.ecomarket.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @Test
    void getAll() {
        when(pedidoService.getAll()).thenReturn(List.of(new Pedido()));
        ResponseEntity<List<Pedido>> response = pedidoController.getAll();
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getById_existente() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setFecha(LocalDateTime.now());
        when(pedidoService.getById(1L)).thenReturn(pedido);

        ResponseEntity<Pedido> response = pedidoController.getById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void getById_noExistente() {
        when(pedidoService.getById(2L)).thenReturn(null);
        ResponseEntity<Pedido> response = pedidoController.getById(2L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void create() {
        Pedido nuevo = new Pedido();
        nuevo.setEstado("Pendiente");
        when(pedidoService.save(nuevo)).thenReturn(nuevo);

        ResponseEntity<Pedido> response = pedidoController.create(nuevo);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Pendiente", response.getBody().getEstado());
    }

    @Test
    void update_existente() {
        when(pedidoService.update(eq(1L), any())).thenReturn(true);
        ResponseEntity<String> response = pedidoController.update(1L, new Pedido());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Pedido actualizado.", response.getBody());
    }

    @Test
    void update_noExistente() {
        when(pedidoService.update(eq(2L), any())).thenReturn(false);
        ResponseEntity<String> response = pedidoController.update(2L, new Pedido());
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void delete_existente() {
        when(pedidoService.delete(1L)).thenReturn(true);
        ResponseEntity<Void> response = pedidoController.delete(1L);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void delete_noExistente() {
        when(pedidoService.delete(2L)).thenReturn(false);
        ResponseEntity<Void> response = pedidoController.delete(2L);
        assertEquals(404, response.getStatusCodeValue());
    }
}

