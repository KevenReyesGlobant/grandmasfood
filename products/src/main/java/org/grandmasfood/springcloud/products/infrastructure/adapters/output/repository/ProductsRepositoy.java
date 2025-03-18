package org.grandmasfood.springcloud.products.infrastructure.adapters.output.repository;

import org.grandmasfood.springcloud.products.infrastructure.adapters.output.entities.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductsRepositoy extends JpaRepository<ProductsEntity, Long> {
    @Query("select p from ProductsEntity p where p.idProduct=:idProduct and p.active=true")
    ProductsEntity findProductsActiveById(Long idProduct);

    @Query("select p from ProductsEntity p where p.uuid=:uuid and p.active=true")
    ProductsEntity findProductsActiveByUuId(UUID uuid);
}
