package com.example.ahorraya.controllers;


import com.example.ahorraya.dtos.CategoriaProductoDTO;
import com.example.ahorraya.services.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @GetMapping
    public ResponseEntity<List<CategoriaProductoDTO>> listar() {
        return ResponseEntity.ok(categoriaProductoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProductoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaProductoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaProductoDTO> crear(@RequestBody CategoriaProductoDTO dto) {
        return new ResponseEntity<>(categoriaProductoService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProductoDTO> actualizar(@PathVariable Long id, @RequestBody CategoriaProductoDTO dto) {
        dto.setIdCategoria(id);
        return ResponseEntity.ok(categoriaProductoService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
