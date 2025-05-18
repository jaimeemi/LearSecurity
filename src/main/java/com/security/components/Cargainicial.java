package com.security.components;

import com.security.models.entities.ProductosEntityes;
import com.security.respositorys.IProductosRepositoryes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Cargainicial {

    private final IProductosRepositoryes productosRepositoryes;

    @Autowired

    public Cargainicial(IProductosRepositoryes productosRepositoryes) {
        this.productosRepositoryes = productosRepositoryes;
    }

    @Bean
    public CommandLineRunner run() {
        log.info("Cargando datos iniciales");
        return args -> {
            if (productosRepositoryes.count() == 0)
                productosRepositoryes.saveAll(List.of(
                    new ProductosEntityes("Laptop", "Portátil 16GB RAM", 10, 1200.0f),
                    new ProductosEntityes("Mouse", "Mouse gamer RGB", 50, 25.5f),
                    new ProductosEntityes("Teclado", "Teclado mecánico", 30, 45.0f),
                    new ProductosEntityes("Monitor", "Monitor 27'' FullHD", 20, 180.0f),
                    new ProductosEntityes("Auriculares", "Headset con micrófono", 15, 60.0f)
            ));
        };
    };
}

