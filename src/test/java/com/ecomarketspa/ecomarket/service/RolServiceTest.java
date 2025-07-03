package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Rol;
import com.ecomarketspa.ecomarket.repository.RolRepository;
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
public class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    @Test
    void getAllRoles() {
        when(rolRepository.findAll()).thenReturn(Arrays.asList(new Rol(), new Rol()));
        List<Rol> result = rolService.getAllRoles();
        assertEquals(2, result.size());
    }

    @Test
    void getRolById_existente() {
        Rol rol = new Rol();
        when(rolRepository.findById(1L)).thenReturn(Optional.of(rol));
        assertNotNull(rolService.getRolById(1L));
    }

    @Test
    void getRolById_noExistente() {
        when(rolRepository.findById(999L)).thenReturn(Optional.empty());
        assertNull(rolService.getRolById(999L));
    }

    @Test
    void createRol() {
        Rol rol = new Rol();
        when(rolRepository.save(rol)).thenReturn(rol);
        assertEquals(rol, rolService.createRol(rol));
    }

    @Test
    void updateRol_existente() {
        Rol rol = new Rol();
        when(rolRepository.existsById(1L)).thenReturn(true);
        when(rolRepository.save(rol)).thenReturn(rol);
        assertTrue(rolService.updateRol(1L, rol));
    }

    @Test
    void updateRol_noExistente() {
        when(rolRepository.existsById(999L)).thenReturn(false);
        assertFalse(rolService.updateRol(999L, new Rol()));
    }

    @Test
    void deleteRol_existente() {
        when(rolRepository.existsById(1L)).thenReturn(true);
        assertTrue(rolService.deleteRol(1L));
    }

    @Test
    void deleteRol_noExistente() {
        when(rolRepository.existsById(999L)).thenReturn(false);
        assertFalse(rolService.deleteRol(999L));
    }
}

