package org.grandmasfood.springcloud.products.infrastructure.adapters.output.repository;

import org.grandmasfood.springcloud.products.infrastructure.adapters.output.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductsRepositoy extends JpaRepository<Products, Long> {
    @Query("select p from Products p where p.id=:id and p.active=true")
    Products findProductsActiveById(Long id);

    @Query("select p from Products p where p.uuid=:uuid and p.active=true")
    Products findProductsActiveByUuId(UUID uuid);
}
