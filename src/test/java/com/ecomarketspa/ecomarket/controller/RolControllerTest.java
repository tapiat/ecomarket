package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Rol;
import com.ecomarketspa.ecomarket.service.RolService;
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
public class RolControllerTest {

    @Mock
    private RolService rolService;

    @InjectMocks
    private RolController rolController;

    @Test
    void getAllRoles() {
        when(rolService.getAllRoles()).thenReturn(List.of(new Rol(1L, "ADMIN")));
        ResponseEntity<List<Rol>> response = rolController.getAllRoles();
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getRolById_existente() {
        Rol rol = new Rol(1L, "USER");
        when(rolService.getRolById(1L)).thenReturn(rol);

        ResponseEntity<Rol> response = rolController.getRolById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("USER", response.getBody().getNombre());
    }

    @Test
    void getRolById_noExistente() {
        when(rolService.getRolById(2L)).thenReturn(null);
        ResponseEntity<Rol> response = rolController.getRolById(2L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void crearRol() {
        Rol nuevo = new Rol(null, "USER");
        when(rolService.createRol(nuevo)).thenReturn(nuevo);

        ResponseEntity<Rol> response = rolController.crearRol(nuevo);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("USER", response.getBody().getNombre());
    }

    @Test
    void actualizarRol_existente() {
        when(rolService.updateRol(1L, new Rol(null, "ADMIN"))).thenReturn(true);
        ResponseEntity<String> response = rolController.actualizarRol(1L, new Rol(null, "ADMIN"));
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Rol actualizado con éxito.", response.getBody());
    }

    @Test
    void actualizarRol_noExistente() {
        when(rolService.updateRol(2L, new Rol(null, "ADMIN"))).thenReturn(false);
        ResponseEntity<String> response = rolController.actualizarRol(2L, new Rol(null, "ADMIN"));
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void eliminarRol_existente() {
        when(rolService.deleteRol(1L)).thenReturn(true);
        ResponseEntity<String> response = rolController.eliminarRol(1L);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void eliminarRol_noExistente() {
        when(rolService.deleteRol(2L)).thenReturn(false);
        ResponseEntity<String> response = rolController.eliminarRol(2L);
        assertEquals(404, response.getStatusCodeValue());
    }
}


