package org.grandmasfood.springcloud.products.config.interfaces;

import org.grandmasfood.springcloud.products.model.dto.ProductsDTO;
import org.grandmasfood.springcloud.products.model.entity.Products;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;


public interface IProductsServies {
    Products createProduct(@Valid ProductsDTO productsDTO);

    Optional<Products> readProductsById(Long id);
    Optional<Products> readProductsByUuId(UUID id);
}
