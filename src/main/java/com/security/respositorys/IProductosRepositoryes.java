package com.security.respositorys;

import com.security.models.entities.ProductosEntityes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductosRepositoryes extends JpaRepository<ProductosEntityes, Integer> {

}
