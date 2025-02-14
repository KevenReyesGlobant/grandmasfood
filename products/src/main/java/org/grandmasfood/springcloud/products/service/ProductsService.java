package org.grandmasfood.springcloud.products.service;


import jakarta.validation.Valid;
import org.grandmasfood.springcloud.products.config.interfaces.IProductsServies;
import org.grandmasfood.springcloud.products.model.dto.ProductsDTO;
import org.grandmasfood.springcloud.products.model.entity.Products;
import org.grandmasfood.springcloud.products.repository.ProductsRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductsService implements IProductsServies {

    @Autowired
    private ProductsRepositoy productsRepositoy;

    @Override
    @Transactional
    public Products createProduct(@Valid ProductsDTO productsDTO) {
        Products products = new Products();
        products.setUuid(productsDTO.uuid());
        products.setDescription(productsDTO.description());
        products.setFantasy_name(productsDTO.fantasy_name());
        products.setCategory(productsDTO.category());
        products.setPrice(productsDTO.price());
        products.setAvailable(productsDTO.available());
        products.setActive(productsDTO.active());


        return productsRepositoy.save(products);
    }
}
