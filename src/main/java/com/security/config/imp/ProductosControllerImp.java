package com.security.config.imp;

import com.security.controller.IProductosController;
import com.security.models.DTO.ProductoDTO;
import com.security.service.IProductosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ProductosControllerImp implements IProductosController {

    protected final IProductosServices productosServices;

    @Autowired
    public ProductosControllerImp(IProductosServices productosServices) {
        this.productosServices = productosServices;
    }

    @Override
    public ResponseEntity<ProductoDTO> producto(int productoId) {
        return productosServices.obtenerProductoPorId(productoId);
    }

    @Override
    public ResponseEntity<List<ProductoDTO>> productos() {
        return productosServices.obtenerProductos();
    }

    @Override
    public ResponseEntity<ProductoDTO> guardar(ProductoDTO productoDTO) {
        return productosServices.grabarProducto(productoDTO);
    }

    @Override
    public ResponseEntity<ProductoDTO> actualizar(int productoId, ProductoDTO productoDTO) {
        return productosServices.actualizarProducto(productoId, productoDTO);
    }

    @Override
    public ProductoDTO eliminar(int productoId) {
        return productosServices.eliminarProducto(productoId);
    }

}