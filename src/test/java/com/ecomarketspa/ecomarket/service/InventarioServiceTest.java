package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Inventario;
import com.ecomarketspa.ecomarket.repository.InventarioRepository;
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
public class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    void getAllInventario() {
        List<Inventario> lista = Arrays.asList(new Inventario(), new Inventario());
        when(inventarioRepository.findAll()).thenReturn(lista);

        List<Inventario> result = inventarioService.getAllInventario();

        assertEquals(2, result.size());
        verify(inventarioRepository).findAll();
    }

    @Test
    void getInventarioByCodigo_existente() {
        Inventario inv = new Inventario();
        inv.setCodigo("INV001");
        when(inventarioRepository.findById("INV001")).thenReturn(Optional.of(inv));

        Inventario result = inventarioService.getInventarioByCodigo("INV001");

        assertNotNull(result);
        assertEquals("INV001", result.getCodigo());
        verify(inventarioRepository).findById("INV001");
    }

    @Test
    void getInventarioByCodigo_noExistente() {
        when(inventarioRepository.findById("INV404")).thenReturn(Optional.empty());

        Inventario result = inventarioService.getInventarioByCodigo("INV404");

        assertNull(result);
        verify(inventarioRepository).findById("INV404");
    }

    @Test
    void addInventory() {
        Inventario inv = new Inventario();
        inv.setCodigo("INV100");
        when(inventarioRepository.save(inv)).thenReturn(inv);

        Inventario result = inventarioService.addInventory(inv);

        assertEquals("INV100", result.getCodigo());
        verify(inventarioRepository).save(inv);
    }

    @Test
    void updateInventory_existente() {
        Inventario nuevo = new Inventario();
        when(inventarioRepository.existsById("INV200")).thenReturn(true);
        when(inventarioRepository.save(nuevo)).thenReturn(nuevo);

        boolean result = inventarioService.updateInventory("INV200", nuevo);

        assertTrue(result);
        verify(inventarioRepository).save(nuevo);
    }

    @Test
    void updateInventory_noExistente() {
        when(inventarioRepository.existsById("INV999")).thenReturn(false);

        boolean result = inventarioService.updateInventory("INV999", new Inventario());

        assertFalse(result);
        verify(inventarioRepository, never()).save(any());
    }

    @Test
    void deleteInventory_existente() {
        when(inventarioRepository.existsById("INV001")).thenReturn(true);

        boolean result = inventarioService.deleteInventory("INV001");

        assertTrue(result);
        verify(inventarioRepository).deleteById("INV001");
    }

    @Test
    void deleteInventory_noExistente() {
        when(inventarioRepository.existsById("INV404")).thenReturn(false);

        boolean result = inventarioService.deleteInventory("INV404");

        assertFalse(result);
        verify(inventarioRepository, never()).deleteById(any());
    }
}

