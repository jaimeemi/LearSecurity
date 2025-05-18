package com.security.controller;

import com.security.models.DTO.ProductoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/producto", produces = "application/json")
@Validated
public interface IProductosController {

    @GetMapping(value = "/{productoId}")
    ResponseEntity<ProductoDTO> producto(@PathVariable int productoId);

    @GetMapping(value = "/listar")
    ResponseEntity<List<ProductoDTO>> productos();

    @PostMapping(value = "/grabar")
    ResponseEntity<ProductoDTO> guardar(@Valid @RequestBody ProductoDTO productoDTO);

    @PutMapping(value = "/actualizar/{productoId}")
    ResponseEntity<ProductoDTO> actualizar(@PathVariable int productoId,@Valid @RequestBody ProductoDTO productoDTO);

    @DeleteMapping(value = "/eliminar/{productoId}")
    ProductoDTO eliminar(@PathVariable int productoId);
}
