package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.DetallePedido;
import com.ecomarketspa.ecomarket.repository.DetallePedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DetallePedidoServiceTest {

    @Mock
    private DetallePedidoRepository repository;

    @InjectMocks
    private DetallePedidoService service;

    @Test
    void getAll() {
        List<DetallePedido> mockList = Arrays.asList(new DetallePedido(), new DetallePedido());
        when(repository.findAll()).thenReturn(mockList);

        List<DetallePedido> result = service.getAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getById_existente() {
        DetallePedido detalle = new DetallePedido();
        when(repository.findById(1L)).thenReturn(Optional.of(detalle));

        DetallePedido result = service.getById(1L);

        assertNotNull(result);
        verify(repository).findById(1L);
    }

    @Test
    void getById_noExistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        DetallePedido result = service.getById(99L);

        assertNull(result);
        verify(repository).findById(99L);
    }

    @Test
    void save() {
        DetallePedido detalle = new DetallePedido();
        when(repository.save(detalle)).thenReturn(detalle);

        DetallePedido result = service.save(detalle);

        assertEquals(detalle, result);
        verify(repository).save(detalle);
    }

    @Test
    void update_existente() {
        DetallePedido detalle = new DetallePedido();
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(detalle)).thenReturn(detalle);

        boolean result = service.update(1L, detalle);

        assertTrue(result);
        verify(repository).save(detalle);
    }

    @Test
    void update_noExistente() {
        DetallePedido detalle = new DetallePedido();
        when(repository.existsById(99L)).thenReturn(false);

        boolean result = service.update(99L, detalle);

        assertFalse(result);
        verify(repository, never()).save(any());
    }

    @Test
    void delete_existente() {
        when(repository.existsById(1L)).thenReturn(true);

        boolean result = service.delete(1L);

        assertTrue(result);
        verify(repository).deleteById(1L);
    }

    @Test
    void delete_noExistente() {
        when(repository.existsById(99L)).thenReturn(false);

        boolean result = service.delete(99L);

        assertFalse(result);
        verify(repository, never()).deleteById(any());
    }
}

