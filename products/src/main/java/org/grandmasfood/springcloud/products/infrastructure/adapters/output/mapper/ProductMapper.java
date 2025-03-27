package org.grandmasfood.springcloud.products.infrastructure.adapters.output.mapper;

import org.grandmasfood.springcloud.products.domain.model.Product;
import org.grandmasfood.springcloud.products.infrastructure.adapters.output.entities.ProductsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductsEntity toProductEntity(Product product);

    Product toProduct(ProductsEntity productsEntity);

    List<Product> toProductList(List<ProductsEntity> productsEntities);
}
