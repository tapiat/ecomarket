package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Inventario;
import com.ecomarketspa.ecomarket.service.InventarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventarioControllerTest {

    @Mock
    private InventarioService inventarioService;

    @InjectMocks
    private InventarioController inventarioController;

    public InventarioControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllInventario() {
        when(inventarioService.getAllInventario()).thenReturn(List.of(new Inventario("I1", "Macetero", 10, "Bodega")));
        ResponseEntity<List<Inventario>> response = inventarioController.getAllInventario();
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getInventarioById_existente() {
        Inventario i = new Inventario("I1", "Macetero", 10, "Bodega");
        when(inventarioService.getInventarioByCodigo("I1")).thenReturn(i);
        ResponseEntity<Inventario> response = inventarioController.getInventarioById("I1");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Macetero", response.getBody().getNombre());
    }

    @Test
    void getInventarioById_noExistente() {
        when(inventarioService.getInventarioByCodigo("X1")).thenReturn(null);
        ResponseEntity<Inventario> response = inventarioController.getInventarioById("X1");
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void createInventario() {
        Inventario nuevo = new Inventario("I2", "Fertilizante", 20, "Almacén");
        when(inventarioService.addInventory(nuevo)).thenReturn(nuevo);
        ResponseEntity<Inventario> response = inventarioController.createInventario(nuevo);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Fertilizante", response.getBody().getNombre());
    }

    @Test
    void actualizarInventario_existente() {
        when(inventarioService.updateInventory(eq("I1"), any())).thenReturn(true);
        ResponseEntity<Inventario> response = inventarioController.actualizarInventario("I1", new Inventario());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void actualizarInventario_noExistente() {
        when(inventarioService.updateInventory(eq("I2"), any())).thenReturn(false);
        ResponseEntity<Inventario> response = inventarioController.actualizarInventario("I2", new Inventario());
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void deleteInventario_existente() {
        when(inventarioService.deleteInventory("I1")).thenReturn(true);
        ResponseEntity<Inventario> response = inventarioController.deleteInventario("I1");
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deleteInventario_noExistente() {
        when(inventarioService.deleteInventory("X1")).thenReturn(false);
        ResponseEntity<Inventario> response = inventarioController.deleteInventario("X1");
        assertEquals(404, response.getStatusCodeValue());
    }
}

