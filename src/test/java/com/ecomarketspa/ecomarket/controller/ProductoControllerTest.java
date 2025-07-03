package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Producto;
import com.ecomarketspa.ecomarket.service.ProductoService;
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
public class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    public ProductoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerTodos() {
        when(productoService.getProducts()).thenReturn(List.of(new Producto("P1", "Nombre", "Desc", 1000)));
        ResponseEntity<List<Producto>> response = productoController.obtenerTodos();
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void obtenerPorCodigo_existente() {
        Producto producto = new Producto("P1", "Nombre", "Desc", 1000);
        when(productoService.getProductByCodigo("P1")).thenReturn(producto);
        ResponseEntity<Producto> response = productoController.obtenerPorCodigo("P1");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Nombre", response.getBody().getNombre());
    }

    @Test
    void obtenerPorCodigo_noExistente() {
        when(productoService.getProductByCodigo("P2")).thenReturn(null);
        ResponseEntity<Producto> response = productoController.obtenerPorCodigo("P2");
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void agregarProducto() {
        Producto producto = new Producto("P1", "Nombre", "Desc", 1000);
        when(productoService.addProduct(producto)).thenReturn(producto);
        ResponseEntity<Producto> response = productoController.agregarProducto(producto);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("P1", response.getBody().getCodigo());
    }

    @Test
    void actualizarProducto_existente() {
        when(productoService.updateProduct(eq("P1"), any())).thenReturn(true);
        ResponseEntity<String> response = productoController.actualizarProducto("P1", new Producto());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void actualizarProducto_noExistente() {
        when(productoService.updateProduct(eq("P2"), any())).thenReturn(false);
        ResponseEntity<String> response = productoController.actualizarProducto("P2", new Producto());
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void eliminarProducto_existente() {
        when(productoService.deleteProduct("P1")).thenReturn(true);
        ResponseEntity<String> response = productoController.eliminarProducto("P1");
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void eliminarProducto_noExistente() {
        when(productoService.deleteProduct("P2")).thenReturn(false);
        ResponseEntity<String> response = productoController.eliminarProducto("P2");
        assertEquals(404, response.getStatusCodeValue());
    }
}
