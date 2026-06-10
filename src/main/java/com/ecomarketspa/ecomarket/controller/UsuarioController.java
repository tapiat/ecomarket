package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Usuario;
import com.ecomarketspa.ecomarket.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id,
                                                      @Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/registro")
    public ResponseEntity<Map<String, Object>> registrarUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.registrarConCodigo(usuario);
        return ResponseEntity.ok(Map.of(
                "id", nuevo.getId(),
                "code", nuevo.getCode(),
                "mensaje", "Usuario registrado. Valida tu cuenta con el código enviado."
        ));
    }

    @PostMapping("/validar/{username}")
    public ResponseEntity<Map<String, String>> validarUsuario(@PathVariable String username,
                                                               @RequestBody Map<String, String> body) {
        String resultado = usuarioService.validarCodigo(username, body.get("code"));
        return ResponseEntity.ok(Map.of("resultado", resultado));
    }
}
