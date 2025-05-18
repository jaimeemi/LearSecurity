package com.security.respositorys.functions;

import com.security.models.DTO.ProductoDTO;
import com.security.models.entities.ProductosEntityes;

import java.util.Optional;

public class RepositoriesFunctions {

    public static ProductosEntityes toEntity(ProductoDTO dto) {
        ProductosEntityes entity = new ProductosEntityes();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        return entity;
    }

    public static ProductoDTO toDto(ProductosEntityes entity) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecio(entity.getPrecio());
        dto.setStock(entity.getStock());
        return dto;
    }

    public static ProductoDTO optionalProductoDTO(Optional<ProductosEntityes> entity) {
        if(entity.get() == null) {
            return null;
        }
        return toDto(entity.get());
    }

}
