package org.grandmasfood.springcloud.products.application.ports.output;

import org.grandmasfood.springcloud.products.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductPersistentPort {

    Product save(Product product);

    Optional<Product> findActiveByUuid(UUID uuid);

    Optional<Product> findActiveById(Long id);

    Optional<List<Product>> findByFantasyName(String fantasyName);


    Product deleteByUuid(UUID uuid);

}
