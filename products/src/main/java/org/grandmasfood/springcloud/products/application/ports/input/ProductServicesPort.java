package org.grandmasfood.springcloud.products.application.ports.input;

import org.grandmasfood.springcloud.products.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductServicesPort {

    Product findById(Long id);

    List<Product> findAll();

    Product save(Product product);

    Product update(UUID uuid, Product product);

    Product findActiveByUuid(UUID Uuid);

    Product findActiveById(Long id);

    Product deleteByUuid(UUID uuid);

}
