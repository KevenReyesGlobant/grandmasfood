package org.grandmasfood.springcloud.products.infrastructure.adapters.output;

import org.grandmasfood.springcloud.products.application.ports.output.ProductPersistentPort;
import org.grandmasfood.springcloud.products.domain.model.Product;
import org.grandmasfood.springcloud.products.infrastructure.adapters.output.mapper.ProductMapper;
import org.grandmasfood.springcloud.products.infrastructure.adapters.output.repository.ProductsRepositoy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductPersistentAdapter implements ProductPersistentPort {

    private final ProductsRepositoy productsRepositoy;
    private final ProductMapper productMapper;


    public ProductPersistentAdapter(ProductsRepositoy productsRepositoy, ProductMapper productMapper) {
        this.productsRepositoy = productsRepositoy;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        product.setFantasyName(product.getFantasyName().toUpperCase());
        return productMapper.toProduct(productsRepositoy.save(productMapper.toProductEntity(product)));
    }

    @Override
    public Optional<Product> findActiveByUuid(UUID uuid) {
        return Optional.ofNullable(productMapper.toProduct(productsRepositoy.findProductsActiveByUuId(uuid)));
    }

    @Override
    public Optional<Product> findActiveById(Long id) {
        return Optional.ofNullable(productMapper.toProduct(productsRepositoy.findProductsActiveById(id)));
    }

    @Override
    public List<Product> findByFantasyName(String fantasyName) {
        return productMapper.toProductList(productsRepositoy.findByFantasyNameContainingAndActiveTrue(fantasyName));
    }

    @Override
    public Product deleteByUuid(UUID uuid) {
        Product product = productMapper.toProduct(productsRepositoy.findProductsActiveByUuId(uuid));
        product.setActive(false);
        return productMapper.toProduct(productsRepositoy.save(productMapper.toProductEntity(product)));
    }
}
