package org.grandmasfood.springcloud.products.application.service;

import org.grandmasfood.springcloud.products.application.ports.input.ProductServicesPort;
import org.grandmasfood.springcloud.products.application.ports.output.ProductPersistentPort;
import org.grandmasfood.springcloud.products.domain.exceptions.ProductNotFoundException;
import org.grandmasfood.springcloud.products.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ProductsService implements ProductServicesPort {

    private final ProductPersistentPort productPersistentPort;

    public ProductsService(ProductPersistentPort productPersistentPort) {
        this.productPersistentPort = productPersistentPort;
    }

    @Override
    public Product save(Product product) {
        return productPersistentPort.save(product);
    }

    @Override
    public Product findActiveByUuid(UUID uuid) {
        return productPersistentPort.findActiveByUuid(uuid).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> findByFantasyName(String fantasyName) {
        return productPersistentPort.findByFantasyName(fantasyName);
    }

    @Override
    public Product findActiveById(Long id) {
        return productPersistentPort.findActiveById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product update(UUID uuid, Product product) {
        return productPersistentPort.findActiveByUuid(uuid).map(saveProduct -> {
            updateField(product.getFantasyName(), saveProduct::setFantasyName);
            updateField(product.getCategory(), saveProduct::setCategory);
            updateField(product.getDescription(), saveProduct::setDescription);
            updateField(product.getPrice(), saveProduct::setPrice);
            return productPersistentPort.save(saveProduct);
        }).orElseThrow(ProductNotFoundException::new);
    }

    private <T> void updateField(T newValue, java.util.function.Consumer<T> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }


    @Override
    public Product deleteByUuid(UUID uuid) {
        return productPersistentPort.deleteByUuid(uuid);
    }
}
