package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.DetallePedido;
import com.ecomarketspa.ecomarket.service.DetallePedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DetallePedidoControllerTest {

    @Mock
    private DetallePedidoService detallePedidoService;

    @InjectMocks
    private DetallePedidoController detallePedidoController;

    @Test
    void getAllDetallePedidos() {
        when(detallePedidoService.getAll()).thenReturn(List.of(new DetallePedido()));
        ResponseEntity<List<DetallePedido>> response = detallePedidoController.getAll();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getDetallePedidoById_existente() {
        DetallePedido detalle = new DetallePedido();
        detalle.setId(1L);
        when(detallePedidoService.getById(1L)).thenReturn(detalle);

        ResponseEntity<DetallePedido> response = detallePedidoController.getById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void getDetallePedidoById_noExistente() {
        when(detallePedidoService.getById(999L)).thenReturn(null);

        ResponseEntity<DetallePedido> response = detallePedidoController.getById(999L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void createDetallePedido() {
        DetallePedido detalle = new DetallePedido();
        when(detallePedidoService.save(detalle)).thenReturn(detalle);

        ResponseEntity<DetallePedido> response = detallePedidoController.create(detalle);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(detalle, response.getBody());
    }

    @Test
    void updateDetallePedido_existente() {
        when(detallePedidoService.update(eq(1L), any())).thenReturn(true);
        ResponseEntity<String> response = detallePedidoController.update(1L, new DetallePedido());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Detalle actualizado.", response.getBody());
    }

    @Test
    void updateDetallePedido_noExistente() {
        when(detallePedidoService.update(eq(999L), any())).thenReturn(false);
        ResponseEntity<String> response = detallePedidoController.update(999L, new DetallePedido());
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void deleteDetallePedido_existente() {
        when(detallePedidoService.delete(1L)).thenReturn(true);
        ResponseEntity<Void> response = detallePedidoController.delete(1L);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deleteDetallePedido_noExistente() {
        when(detallePedidoService.delete(999L)).thenReturn(false);
        ResponseEntity<Void> response = detallePedidoController.delete(999L);
        assertEquals(404, response.getStatusCodeValue());
    }
}

