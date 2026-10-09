package com.example.ahorraya.controllers;


import com.example.ahorraya.dtos.ResenaDTO;
import com.example.ahorraya.dtos.ResenaRegistroDTO;
import com.example.ahorraya.services.ClienteService;
import com.example.ahorraya.services.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ResenaDTO> registrar(Authentication authentication, @RequestBody ResenaRegistroDTO dto) {
        Long idCliente = clienteService.buscarPorCorreo(authentication.getName()).getIdCliente();
        return new ResponseEntity<>(resenaService.registrar(idCliente, dto), HttpStatus.CREATED);
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<ResenaDTO>> listarPorProducto(@PathVariable Long idProducto) {
        return ResponseEntity.ok(resenaService.listarPorProducto(idProducto));
    }

}
