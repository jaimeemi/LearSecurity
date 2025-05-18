package com.security.service.imp;

import com.security.models.DTO.ProductoDTO;
import com.security.respositorys.IProductosRepositoryes;
import com.security.respositorys.functions.RepositoriesFunctions;
import com.security.service.IProductosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServicesImp implements IProductosServices {

    protected final IProductosRepositoryes productosRepositoryes;

    @Autowired
    public ProductosServicesImp(IProductosRepositoryes productosRepositoryes) {
        this.productosRepositoryes = productosRepositoryes;
    }

    @Override
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(int productoId) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO =  RepositoriesFunctions.optionalProductoDTO (productosRepositoryes.findById(productoId) );

        return new ResponseEntity<>(productoDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductoDTO>> obtenerProductos() {
        List<ProductoDTO> productos = new java.util.ArrayList<>();

        productosRepositoryes.findAll().forEach(producto -> {
            productos.add(RepositoriesFunctions.toDto(producto));
        });

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoDTO> grabarProducto(ProductoDTO productoDTO) {
        productosRepositoryes.save(RepositoriesFunctions.toEntity(productoDTO));
        return new ResponseEntity<>(productoDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoDTO> actualizarProducto(int productoId, ProductoDTO productoDTO) {
        ProductoDTO productoActualizado;
        productoActualizado = RepositoriesFunctions.optionalProductoDTO( productosRepositoryes.findById(productoId) );

        productoActualizado.setNombre(productoDTO.getNombre());
        productoActualizado.setPrecio(productoDTO.getPrecio());
        productoActualizado.setStock(productoDTO.getStock());
        productoActualizado.setId_categoria(productoDTO.getId_categoria());
        productoActualizado.setId_proveedor(productoDTO.getId_proveedor());

        productosRepositoryes.save( RepositoriesFunctions.toEntity( productoActualizado ) );

        if (productoActualizado != null)
            productosRepositoryes.save( RepositoriesFunctions.toEntity( productoActualizado ) );
        else
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(productoActualizado);
    }

    @Override
    public ProductoDTO eliminarProducto(int productoId) {
        ProductoDTO productoDTO = RepositoriesFunctions.optionalProductoDTO(  productosRepositoryes.findById(productoId) );
        if (productoDTO.equals(null))
            return null;
        else
            return productoDTO;
    }
}
