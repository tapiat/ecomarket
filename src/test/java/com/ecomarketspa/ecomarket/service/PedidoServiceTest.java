package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Pedido;
import com.ecomarketspa.ecomarket.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void getAll() {
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(new Pedido(), new Pedido()));
        List<Pedido> result = pedidoService.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void getById_existente() {
        Pedido pedido = new Pedido();
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        assertNotNull(pedidoService.getById(1L));
    }

    @Test
    void getById_noExistente() {
        when(pedidoRepository.findById(999L)).thenReturn(Optional.empty());
        assertNull(pedidoService.getById(999L));
    }

    @Test
    void save() {
        Pedido pedido = new Pedido();
        when(pedidoRepository.save(pedido)).thenReturn(pedido);
        assertEquals(pedido, pedidoService.save(pedido));
    }

    @Test
    void update_existente() {
        Pedido pedido = new Pedido();
        when(pedidoRepository.existsById(1L)).thenReturn(true);
        when(pedidoRepository.save(pedido)).thenReturn(pedido);
        assertTrue(pedidoService.update(1L, pedido));
    }

    @Test
    void update_noExistente() {
        when(pedidoRepository.existsById(999L)).thenReturn(false);
        assertFalse(pedidoService.update(999L, new Pedido()));
    }

    @Test
    void delete_existente() {
        when(pedidoRepository.existsById(1L)).thenReturn(true);
        assertTrue(pedidoService.delete(1L));
    }

    @Test
    void delete_noExistente() {
        when(pedidoRepository.existsById(999L)).thenReturn(false);
        assertFalse(pedidoService.delete(999L));
    }
}

