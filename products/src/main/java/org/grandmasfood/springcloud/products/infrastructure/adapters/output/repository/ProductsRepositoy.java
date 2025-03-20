package org.grandmasfood.springcloud.products.infrastructure.adapters.output.repository;

import org.grandmasfood.springcloud.products.infrastructure.adapters.output.entities.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface ProductsRepositoy extends JpaRepository<ProductsEntity, Long> {
    @Query("select p from ProductsEntity p where p.idProduct=:idProduct and p.active=true")
    ProductsEntity findProductsActiveById(Long idProduct);

    @Query("select p from ProductsEntity p where p.uuid=:uuid and p.active=true")
    ProductsEntity findProductsActiveByUuId(UUID uuid);

    @Query("select p from ProductsEntity p where p.fantasyName like concat('%', :fantasyName, '%') and p.active = true")
    List<ProductsEntity> findProductsByFantasyName(@Param("fantasyName") String fantasyName);
}
