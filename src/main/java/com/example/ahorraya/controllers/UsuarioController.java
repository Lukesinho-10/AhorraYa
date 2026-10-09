package com.example.ahorraya.controllers;


import com.example.ahorraya.dtos.AuthResponseDTO;
import com.example.ahorraya.dtos.LoginDTO;
import com.example.ahorraya.dtos.UsuarioRegistroDTO;
import com.example.ahorraya.dtos.UsuarioResponseDTO;
import com.example.ahorraya.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<AuthResponseDTO> registrar(@RequestBody UsuarioRegistroDTO dto) {
        return new ResponseEntity<>(usuarioService.registrar(dto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO dto) {
        return new ResponseEntity<>(usuarioService.login(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

}
