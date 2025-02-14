package org.grandmasfood.springcloud.products.service;


import jakarta.validation.Valid;
import org.grandmasfood.springcloud.products.config.interfaces.IProductsServies;
import org.grandmasfood.springcloud.products.model.dto.ProductsDTO;
import org.grandmasfood.springcloud.products.model.entity.Products;
import org.springframework.stereotype.Service;

@Service
public class ProductsService implements IProductsServies {
    @Override
    public Products createClient(@Valid ProductsDTO productsDTO) {
        return null;
    }
}
