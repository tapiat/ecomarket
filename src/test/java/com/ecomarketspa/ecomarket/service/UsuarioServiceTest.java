package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Usuario;
import com.ecomarketspa.ecomarket.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario(1L, "testuser", "pass", "mail@test.com", "USER");
    }

    @Test
    void obtenerTodos() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        assertFalse(usuarios.isEmpty());
    }

    @Test
    void obtenerPorId_existente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        assertNotNull(usuarioService.obtenerPorId(1L));
    }

    @Test
    void obtenerPorId_noExistente() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(usuarioService.obtenerPorId(99L));
    }

    @Test
    void crearUsuario() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario creado = usuarioService.crearUsuario(usuario);
        assertEquals("testuser", creado.getUsername());
    }

    @Test
    void actualizarUsuario_existente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any())).thenReturn(usuario);

        Usuario actualizado = usuarioService.actualizarUsuario(1L, new Usuario(null, "nuevo", "123", "nuevo@mail", "ADMIN"));
        assertEquals("nuevo", actualizado.getUsername());
    }

    @Test
    void actualizarUsuario_noExistente() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(usuarioService.actualizarUsuario(99L, usuario));
    }

    @Test
    void eliminarUsuario_existente() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        assertTrue(usuarioService.eliminarUsuario(1L));
    }

    @Test
    void eliminarUsuario_noExistente() {
        when(usuarioRepository.existsById(99L)).thenReturn(false);
        assertFalse(usuarioService.eliminarUsuario(99L));
    }

    @Test
    void registrarConCodigo() {
        when(usuarioRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        Usuario registrado = usuarioService.registrarConCodigo(usuario);
        assertNotNull(registrado.getCode());
    }

    @Test
    void validarCodigo_correcto() {
        usuario.setCode("1234");
        when(usuarioRepository.findByUsername("testuser")).thenReturn(usuario);

        String resultado = usuarioService.validarCodigo("testuser", "1234");
        assertEquals("VALIDADO", resultado);
        assertTrue(usuario.isValid());
    }

    @Test
    void validarCodigo_incorrecto() {
        usuario.setCode("9999");
        when(usuarioRepository.findByUsername("testuser")).thenReturn(usuario);

        String resultado = usuarioService.validarCodigo("testuser", "0000");
        assertEquals("Código incorrecto.", resultado);
    }

    @Test
    void validarCodigo_usuarioNoEncontrado() {
        when(usuarioRepository.findByUsername("desconocido")).thenReturn(null);
        String resultado = usuarioService.validarCodigo("desconocido", "1234");
        assertEquals("Usuario no encontrado.", resultado);
    }
}

