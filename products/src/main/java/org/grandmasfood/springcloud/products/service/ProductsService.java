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
    public Products updateProduct(@Valid ProductsDTO productsDTO, @Valid UUID id) {
        try {
            Optional<Products> productUpdate = Optional.ofNullable(productsRepositoy.findProductsActiveByUuId(id));
            if (productUpdate.isPresent()) {
                Products product = productUpdate.get();
                updateField(productsDTO.description(), product::setDescription);
                updateField(productsDTO.fantasyName(), product::setFantasyName);
                updateField(productsDTO.category(), product::setCategory);
                updateField(productsDTO.price(), product::setPrice);
                updateField(productsDTO.available(), product::setAvailable);

                return productsRepositoy.save(product);
            }
            return null;

        } catch (RuntimeException e) {
            throw new RuntimeException("Error updating client: " + e.getMessage());
        }

    }

    private <T> void updateField(T newValue, java.util.function.Consumer<T> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
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

    @Override
    public Optional<Products> deleteProductsByUuId(UUID id) {
        Products product=productsRepositoy.findProductsActiveByUuId(id);
        if(product!=null){
            product.setActive(false);
            productsRepositoy.save(product);
            return Optional.of(product);
        }
        return Optional.empty();
    }


}
