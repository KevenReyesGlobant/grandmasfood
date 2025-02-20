package org.grandmasfood.springcloud.products.service;


import jakarta.validation.Valid;
import org.grandmasfood.springcloud.products.config.interfaces.IProductsServies;
import org.grandmasfood.springcloud.products.config.uuid.GeneratedUuId;
import org.grandmasfood.springcloud.products.model.dto.ProductsDTO;
import org.grandmasfood.springcloud.products.model.entity.Products;
import org.grandmasfood.springcloud.products.repository.ProductsRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService implements IProductsServies {

    @Autowired
    private ProductsRepositoy productsRepositoy;

    @Autowired
    private GeneratedUuId generatedUuId;


    @Override
    @Transactional
    public Products createProduct(@Valid ProductsDTO productsDTO) {
        Products products = new Products();

        products.setUuid(generatedUuId.generateUuid());
        products.setDescription(productsDTO.description());
        products.setFantasyName(productsDTO.fantasyName().toUpperCase());
        products.setCategory(productsDTO.category());
        products.setPrice(productsDTO.price());
        products.setAvailable(productsDTO.available());
        products.setActive(productsDTO.active());


        return productsRepositoy.save(products);
    }

    @Override
    @Transactional
    public Optional<Products> readProductsById(Long id) {
        return Optional.ofNullable(productsRepositoy.findProductsActiveById(id));
    }

    @Override
    @Transactional
    public Optional<Products> readProductsByUuId(UUID id) {

        return Optional.ofNullable(productsRepositoy.findProductsActiveByUuId(id));
    }


}
