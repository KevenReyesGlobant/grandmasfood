package org.grandmasfood.springcloud.products.config.interfaces;

import org.grandmasfood.springcloud.products.model.dto.ProductsDTO;
import org.grandmasfood.springcloud.products.model.entity.Products;
import jakarta.validation.Valid;


public interface IProductsServies {
    Products createClient(@Valid ProductsDTO productsDTO);

}
