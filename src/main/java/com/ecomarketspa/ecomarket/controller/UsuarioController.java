package com.ecomarketspa.ecomarket.controller;

import com.ecomarketspa.ecomarket.model.Usuario;
import com.ecomarketspa.ecomarket.service.UsuarioService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 🔹 CRUD: Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // 🔹 CRUD: Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 CRUD: Crear usuario (versión básica sin validación)
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    // 🔹 CRUD: Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 CRUD: Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (usuarioService.eliminarUsuario(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Registro con generación de código único
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.registrarConCodigo(usuario);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("id", nuevo.getId());
        respuesta.put("code", nuevo.getCode());
        return ResponseEntity.ok(respuesta);
    }

    // 🔹 Validación del código para activar la cuenta
    @PostMapping("/validar/{username}")
    public ResponseEntity<?> validarUsuario(@PathVariable String username, @RequestBody Map<String, String> body) {
        String code = body.get("code");
        String resultado = usuarioService.validarCodigo(username, code);

        if (resultado.equals("VALIDADO")) {
            return ResponseEntity.ok("Usuario validado exitosamente.");
        } else {
            return ResponseEntity.badRequest().body(resultado);
        }
    }
}
