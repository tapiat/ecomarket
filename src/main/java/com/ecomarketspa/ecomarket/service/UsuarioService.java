package com.ecomarketspa.ecomarket.service;

import com.ecomarketspa.ecomarket.exception.DuplicateResourceException;
import com.ecomarketspa.ecomarket.exception.ResourceNotFoundException;
import com.ecomarketspa.ecomarket.model.Usuario;
import com.ecomarketspa.ecomarket.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }

    public Usuario crearUsuario(Usuario usuario) {
        validarDuplicados(usuario);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario datos) {
        Usuario usuario = obtenerPorId(id);
        usuario.setUsername(datos.getUsername());
        usuario.setEmail(datos.getEmail());
        usuario.setRol(datos.getRol());
        // Solo actualiza password si viene con valor
        if (datos.getPassword() != null && !datos.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(datos.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario registrarConCodigo(Usuario usuario) {
        validarDuplicados(usuario);
        usuario.setValid(false);
        usuario.setCode(UUID.randomUUID().toString());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public String validarCodigo(String username, String code) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new ResourceNotFoundException("Usuario no encontrado: " + username);
        }
        if (usuario.isValid()) {
            return "El usuario ya está validado.";
        }
        if (!usuario.getCode().equals(code)) {
            return "Código incorrecto.";
        }

        usuario.setValid(true);
        usuarioRepository.save(usuario);
        return "VALIDADO";
    }

    private void validarDuplicados(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new DuplicateResourceException("El username ya está en uso: " + usuario.getUsername());
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado: " + usuario.getEmail());
        }
    }
}
