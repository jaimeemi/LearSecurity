package com.security.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private int id;
    private String nombre;
    private String descripcion;
    private int stock;
    private float precio;
    private int id_categoria;
    private int id_proveedor;

}
