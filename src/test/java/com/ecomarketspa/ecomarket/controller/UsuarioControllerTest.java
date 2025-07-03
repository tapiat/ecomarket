package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Usuario;
import com.ecomarketspa.ecomarket.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerTodos() {
        List<Usuario> usuarios = List.of(new Usuario(1L, "user", "pass", "email@test.com", "ADMIN"));
        when(usuarioService.obtenerTodos()).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> response = usuarioController.obtenerTodos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void obtenerPorId_existente() {
        Usuario user = new Usuario(1L, "user", "pass", "email@test.com", "ADMIN");
        when(usuarioService.obtenerPorId(1L)).thenReturn(user);

        ResponseEntity<Usuario> response = usuarioController.obtenerPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("user", response.getBody().getUsername());
    }

    @Test
    void obtenerPorId_noExistente() {
        when(usuarioService.obtenerPorId(99L)).thenReturn(null);

        ResponseEntity<Usuario> response = usuarioController.obtenerPorId(99L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void crearUsuario() {
        Usuario user = new Usuario(1L, "user", "pass", "email@test.com", "ADMIN");
        when(usuarioService.crearUsuario(user)).thenReturn(user);

        ResponseEntity<Usuario> response = usuarioController.crearUsuario(user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("user", response.getBody().getUsername());
    }

    @Test
    void actualizarUsuario_existente() {
        Usuario actualizado = new Usuario(1L, "nuevo", "pass", "email@test.com", "USER");
        when(usuarioService.actualizarUsuario(eq(1L), any())).thenReturn(actualizado);

        ResponseEntity<Usuario> response = usuarioController.actualizarUsuario(1L, actualizado);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("nuevo", response.getBody().getUsername());
    }

    @Test
    void actualizarUsuario_noExistente() {
        when(usuarioService.actualizarUsuario(eq(99L), any())).thenReturn(null);

        ResponseEntity<Usuario> response = usuarioController.actualizarUsuario(99L, new Usuario());

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void eliminarUsuario_existente() {
        when(usuarioService.eliminarUsuario(1L)).thenReturn(true);

        ResponseEntity<Void> response = usuarioController.eliminarUsuario(1L);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void eliminarUsuario_noExistente() {
        when(usuarioService.eliminarUsuario(99L)).thenReturn(false);

        ResponseEntity<Void> response = usuarioController.eliminarUsuario(99L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void registrarUsuario() {
        Usuario nuevo = new Usuario(1L, "user", "pass", "mail@mail.com", "ADMIN");
        nuevo.setCode("ABC123");

        when(usuarioService.registrarConCodigo(any())).thenReturn(nuevo);

        ResponseEntity<?> response = usuarioController.registrarUsuario(nuevo);

        assertEquals(200, response.getStatusCodeValue());

        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals(1L, body.get("id"));
        assertEquals("ABC123", body.get("code"));
    }

    @Test
    void validarUsuario_exito() {
        when(usuarioService.validarCodigo("user", "ABC123")).thenReturn("VALIDADO");

        Map<String, String> body = Map.of("code", "ABC123");

        ResponseEntity<?> response = usuarioController.validarUsuario("user", body);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Usuario validado exitosamente.", response.getBody());
    }

    @Test
    void validarUsuario_falla() {
        when(usuarioService.validarCodigo("user", "WRONG")).thenReturn("Código inválido.");

        Map<String, String> body = Map.of("code", "WRONG");

        ResponseEntity<?> response = usuarioController.validarUsuario("user", body);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Código inválido.", response.getBody());
    }
}

