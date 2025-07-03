package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Envio;
import com.ecomarketspa.ecomarket.repository.EnvioRepository;
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
public class EnvioServiceTest {

    @Mock
    private EnvioRepository repository;

    @InjectMocks
    private EnvioService service;

    @Test
    void getAll() {
        List<Envio> mockList = Arrays.asList(new Envio(), new Envio());
        when(repository.findAll()).thenReturn(mockList);

        List<Envio> result = service.getAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void getById_existente() {
        Envio envio = new Envio();
        when(repository.findById(1L)).thenReturn(Optional.of(envio));

        Envio result = service.getById(1L);

        assertNotNull(result);
        verify(repository).findById(1L);
    }

    @Test
    void getById_noExistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Envio result = service.getById(99L);

        assertNull(result);
        verify(repository).findById(99L);
    }

    @Test
    void save() {
        Envio envio = new Envio();
        when(repository.save(envio)).thenReturn(envio);

        Envio result = service.save(envio);

        assertEquals(envio, result);
        verify(repository).save(envio);
    }

    @Test
    void update_existente() {
        Envio envio = new Envio();
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(envio)).thenReturn(envio);

        boolean result = service.update(1L, envio);

        assertTrue(result);
        verify(repository).save(envio);
    }

    @Test
    void update_noExistente() {
        Envio envio = new Envio();
        when(repository.existsById(99L)).thenReturn(false);

        boolean result = service.update(99L, envio);

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

