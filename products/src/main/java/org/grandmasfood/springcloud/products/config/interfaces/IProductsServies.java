package org.grandmasfood.springcloud.products.config.interfaces;

import org.grandmasfood.springcloud.products.model.dto.ProductsDTO;
import org.grandmasfood.springcloud.products.model.entity.Products;
import jakarta.validation.Valid;

import java.util.Optional;


public interface IProductsServies {
    Products createProduct(@Valid ProductsDTO productsDTO);

    Optional<Products> readProductsById(Long id);
}
