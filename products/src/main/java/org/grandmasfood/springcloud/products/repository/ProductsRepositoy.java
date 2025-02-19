package org.grandmasfood.springcloud.products.repository;

import org.grandmasfood.springcloud.products.model.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductsRepositoy extends JpaRepository<Products, Long> {
    @Query("select p from Products p where p.id=:id and p.active=true")
    Products findProductsActiveById(Long id);

    @Query("select p from Products p where p.uuid=:uuid and p.active=true")
    Products findProductsActiveByUuId(UUID uuid);
}
