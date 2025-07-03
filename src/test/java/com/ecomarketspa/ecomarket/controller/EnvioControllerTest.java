package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Envio;
import com.ecomarketspa.ecomarket.service.EnvioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnvioControllerTest {

    @Mock
    private EnvioService envioService;

    @InjectMocks
    private EnvioController envioController;

    @Test
    void getAllEnvios() {
        when(envioService.getAll()).thenReturn(List.of(
                new Envio(1L, LocalDate.now(), "En camino", "Calle Falsa 123", null)
        ));
        ResponseEntity<List<Envio>> response = envioController.getAll();
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getEnvioById_existente() {
        Envio envio = new Envio(1L, LocalDate.now(), "Entregado", "Calle 1", null);
        when(envioService.getById(1L)).thenReturn(envio);
        ResponseEntity<Envio> response = envioController.getById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Entregado", response.getBody().getEstado());
    }

    @Test
    void getEnvioById_noExistente() {
        when(envioService.getById(2L)).thenReturn(null);
        ResponseEntity<Envio> response = envioController.getById(2L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void createEnvio() {
        Envio nuevo = new Envio(null, LocalDate.now(), "Preparando", "Av. Siempre Viva", null);
        when(envioService.save(nuevo)).thenReturn(nuevo);
        ResponseEntity<Envio> response = envioController.create(nuevo);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Preparando", response.getBody().getEstado());
    }

    @Test
    void updateEnvio_existente() {
        when(envioService.update(eq(1L), any())).thenReturn(true);
        ResponseEntity<String> response = envioController.update(1L, new Envio());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Envío actualizado.", response.getBody());
    }

    @Test
    void updateEnvio_noExistente() {
        when(envioService.update(eq(2L), any())).thenReturn(false);
        ResponseEntity<String> response = envioController.update(2L, new Envio());
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void deleteEnvio_existente() {
        when(envioService.delete(1L)).thenReturn(true);
        ResponseEntity<Void> response = envioController.delete(1L);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deleteEnvio_noExistente() {
        when(envioService.delete(2L)).thenReturn(false);
        ResponseEntity<Void> response = envioController.delete(2L);
        assertEquals(404, response.getStatusCodeValue());
    }
}

