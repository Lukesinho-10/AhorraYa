package com.example.ahorraya.controllers;


import com.example.ahorraya.dtos.CompraDTO;
import com.example.ahorraya.dtos.CompraRegistroDTO;
import com.example.ahorraya.services.ClienteService;
import com.example.ahorraya.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<CompraDTO> registrar(Authentication authentication, @RequestBody CompraRegistroDTO dto) {
        Long idCliente = clienteService.buscarPorCorreo(authentication.getName()).getIdCliente();
        return new ResponseEntity<>(compraService.registrar(idCliente, dto), HttpStatus.CREATED);
    }

    @GetMapping("/mis-compras")
    public ResponseEntity<List<CompraDTO>> misCompras(Authentication authentication) {
        Long idCliente = clienteService.buscarPorCorreo(authentication.getName()).getIdCliente();
        return ResponseEntity.ok(compraService.listarPorCliente(idCliente));
    }

    @GetMapping("/{idCompra}")
    public ResponseEntity<CompraDTO> buscarPorId(@PathVariable Long idCompra) {
        return ResponseEntity.ok(compraService.buscarPorId(idCompra));
    }

}
