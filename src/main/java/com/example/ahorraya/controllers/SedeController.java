package com.example.ahorraya.controllers;


import com.example.ahorraya.dtos.SedeDTO;
import com.example.ahorraya.services.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sedes")
public class SedeController {

    @Autowired
    private SedeService sedeService;

    @GetMapping
    public ResponseEntity<List<SedeDTO>> listar() {
        return ResponseEntity.ok(sedeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(sedeService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<SedeDTO> crear(@RequestBody SedeDTO dto) {
        return new ResponseEntity<>(sedeService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SedeDTO> actualizar(@PathVariable Long id, @RequestBody SedeDTO dto) {
        dto.setIdSede(id);
        return ResponseEntity.ok(sedeService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sedeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
