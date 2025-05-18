package com.security.controller;

import com.security.models.DTO.ProductoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/producto", produces = "application/json")
public interface IProductosController {

    @GetMapping(value = "/{productoId}")
    ResponseEntity<ProductoDTO> producto(@PathVariable int productoId);

    @GetMapping(value = "/listar")
    ResponseEntity<List<ProductoDTO>> productos();

    @PostMapping(value = "/grabar")
    ResponseEntity<ProductoDTO> guardar(@RequestBody ProductoDTO productoDTO);

    @PutMapping(value = "/actualizar/{productoId}")
    ResponseEntity<ProductoDTO> actualizar(@PathVariable int productoId, @RequestBody ProductoDTO productoDTO);

    @DeleteMapping(value = "/eliminar/{productoId}")
    ProductoDTO eliminar(@PathVariable int productoId);
}
