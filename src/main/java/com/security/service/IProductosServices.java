package com.security.service;

import com.security.models.DTO.ProductoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductosServices {

    public ResponseEntity<ProductoDTO> obtenerProductoPorId(int productoId);

    public ResponseEntity<List<ProductoDTO>> obtenerProductos();

    public ResponseEntity<ProductoDTO> grabarProducto(ProductoDTO productoDTO);

    public ResponseEntity<ProductoDTO> actualizarProducto(int productoId, ProductoDTO productoDTO);

    public ProductoDTO eliminarProducto(int productoId);

}