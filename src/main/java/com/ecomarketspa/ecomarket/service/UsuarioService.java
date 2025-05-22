package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.model.Usuario;
import com.ecomarketspa.ecomarket.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 🔹 CRUD: Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // 🔹 CRUD: Obtener usuario por ID
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // 🔹 CRUD: Crear usuario (modo simple, sin código de validación)
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // 🔹 CRUD: Actualizar usuario
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Optional<Usuario> existente = usuarioRepository.findById(id);
        if (existente.isPresent()) {
            Usuario actual = existente.get();
            actual.setUsername(usuario.getUsername());
            actual.setPassword(usuario.getPassword());
            actual.setEmail(usuario.getEmail());
            actual.setRol(usuario.getRol());
            return usuarioRepository.save(actual);
        }
        return null;
    }

    // 🔹 CRUD: Eliminar usuario
    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ REGISTRO CON CÓDIGO (genera UUID y deja isValid=false)
    public Usuario registrarConCodigo(Usuario usuario) {
        usuario.setIsValid(false);
        usuario.setCode(UUID.randomUUID().toString());
        return usuarioRepository.save(usuario);
    }

    // ✅ VALIDAR USUARIO con código único
    public String validarCodigo(String username, String code) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            return "Usuario no encontrado.";
        }

        if (usuario.isValid()) {
            return "El usuario ya está validado.";
        }

        if (!usuario.getCode().equals(code)) {
            return "Código incorrecto.";
        }

        usuario.setIsValid(true);
        usuarioRepository.save(usuario);
        return "VALIDADO";
    }
}
