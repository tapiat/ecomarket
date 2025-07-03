package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Producto;
import com.ecomarketspa.ecomarket.repository.ProductoRepository;
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
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void getProducts() {
        when(productoRepository.findAll()).thenReturn(Arrays.asList(new Producto(), new Producto()));
        List<Producto> result = productoService.getProducts();
        assertEquals(2, result.size());
    }

    @Test
    void getProductByCodigo_existente() {
        Producto producto = new Producto("P001", "Nombre", "Desc", 1000);
        when(productoRepository.findById("P001")).thenReturn(Optional.of(producto));
        assertNotNull(productoService.getProductByCodigo("P001"));
    }

    @Test
    void getProductByCodigo_noExistente() {
        when(productoRepository.findById("X999")).thenReturn(Optional.empty());
        assertNull(productoService.getProductByCodigo("X999"));
    }

    @Test
    void addProduct() {
        Producto producto = new Producto("P001", "Nombre", "Desc", 1000);
        when(productoRepository.save(producto)).thenReturn(producto);
        assertEquals(producto, productoService.addProduct(producto));
    }

    @Test
    void updateProduct_existente() {
        Producto producto = new Producto("P001", "Nombre", "Desc", 1000);
        when(productoRepository.existsById("P001")).thenReturn(true);
        when(productoRepository.save(producto)).thenReturn(producto);
        assertTrue(productoService.updateProduct("P001", producto));
    }

    @Test
    void updateProduct_noExistente() {
        when(productoRepository.existsById("P999")).thenReturn(false);
        assertFalse(productoService.updateProduct("P999", new Producto()));
    }

    @Test
    void deleteProduct_existente() {
        when(productoRepository.existsById("P001")).thenReturn(true);
        assertTrue(productoService.deleteProduct("P001"));
    }

    @Test
    void deleteProduct_noExistente() {
        when(productoRepository.existsById("P999")).thenReturn(false);
        assertFalse(productoService.deleteProduct("P999"));
    }
}

