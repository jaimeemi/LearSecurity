package com.security.controller.imp;

import com.security.controller.IProductosController;
import com.security.models.DTO.ProductoDTO;
import com.security.service.IProductosServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ProductosControllerImp implements IProductosController {

    protected final IProductosServices productosServices;

    @Autowired
    public ProductosControllerImp(IProductosServices productosServices) {
        this.productosServices = productosServices;
    }

    @Override
    public ResponseEntity<ProductoDTO> producto(int productoId) {
        log.info("Llamada al servicio de listar el producto X productos");
        return productosServices.obtenerProductoPorId(productoId);
    }

    @Override
    public ResponseEntity<List<ProductoDTO>> productos() {
        log.info("Llamada al servicio de listar todos los productos");
        return productosServices.obtenerProductos();
    }

    @Override
    public ResponseEntity<ProductoDTO> guardar(@Valid @RequestBody ProductoDTO productoDTO) {
        log.info("Llamada al servicio de guardar producto");
        return productosServices.grabarProducto(productoDTO);
    }

    @Override
    public ResponseEntity<ProductoDTO> actualizar(int productoId, @Valid @RequestBody ProductoDTO productoDTO) {
        log.info("Llamada al servicio de actualizar lo producto");
        return productosServices.actualizarProducto(productoId, productoDTO);
    }

    @Override
    public ProductoDTO eliminar(int productoId) {
        log.info("Llamada al servicio de eliminar lo producto");
        return productosServices.eliminarProducto(productoId);
    }

}