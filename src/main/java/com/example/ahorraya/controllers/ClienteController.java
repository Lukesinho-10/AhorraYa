package com.example.ahorraya.controllers;


import com.example.ahorraya.dtos.ClienteDTO;
import com.example.ahorraya.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ClienteDTO> buscarPorIdUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(clienteService.buscarPorIdUsuario(idUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.actualizar(id, dto));
    }

    @GetMapping("/mi-perfil")
    public ResponseEntity<ClienteDTO> miPerfil(Authentication authentication) {
        String correo = authentication.getName();
        return ResponseEntity.ok(clienteService.buscarPorCorreo(correo));
    }

}
