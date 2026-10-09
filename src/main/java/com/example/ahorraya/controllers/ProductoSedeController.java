package com.example.ahorraya.controllers;


import com.example.ahorraya.dtos.ProductoSedeDTO;
import com.example.ahorraya.services.ProductoSedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos-sede")
public class ProductoSedeController {

    @Autowired
    private ProductoSedeService productoSedeService;

    @GetMapping
    public ResponseEntity<List<ProductoSedeDTO>> listar() {
        return ResponseEntity.ok(productoSedeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoSedeDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoSedeService.buscarPorId(id));
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<ProductoSedeDTO>> buscarPorProducto(@PathVariable Long idProducto) {
        return ResponseEntity.ok(productoSedeService.buscarPorProducto(idProducto));
    }

    @GetMapping("/distrito/{distrito}")
    public ResponseEntity<List<ProductoSedeDTO>> buscarPorDistrito(@PathVariable String distrito) {
        return ResponseEntity.ok(productoSedeService.buscarPorDistrito(distrito));
    }

    @PostMapping
    public ResponseEntity<ProductoSedeDTO> crear(@RequestBody ProductoSedeDTO dto) {
        return new ResponseEntity<>(productoSedeService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoSedeDTO> actualizar(@PathVariable Long id, @RequestBody ProductoSedeDTO dto) {
        dto.setIdProductoSede(id);
        return ResponseEntity.ok(productoSedeService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoSedeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
