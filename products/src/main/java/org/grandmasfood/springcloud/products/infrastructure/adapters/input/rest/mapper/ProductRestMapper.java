package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.mapper;

import org.grandmasfood.springcloud.products.domain.model.Product;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.request.ProductsCreateRequestDTO;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {

    Product toProduct(ProductsCreateRequestDTO productsCreateRequestDTO);

    ProductResponse toProductResponseDTO(Product product);

    List<ProductResponse> listToProductResponseDTO(List<Product> product);

    default List<String> stringToList(String value) {
        return Collections.singletonList(value);
    }

}
