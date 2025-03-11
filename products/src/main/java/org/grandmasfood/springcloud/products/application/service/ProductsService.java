package org.grandmasfood.springcloud.products.application.service;

import org.grandmasfood.springcloud.products.application.ports.input.ProductServicesPort;
import org.grandmasfood.springcloud.products.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductsService implements ProductServicesPort {

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product update(UUID uuid, Product product) {
        return null;
    }

    @Override
    public Product findActiveByUuid(UUID Uuid) {
        return null;
    }

    @Override
    public Product findActiveById(Long id) {
        return null;
    }

    @Override
    public Product deleteByUuid(UUID uuid) {
        return null;
    }
}
