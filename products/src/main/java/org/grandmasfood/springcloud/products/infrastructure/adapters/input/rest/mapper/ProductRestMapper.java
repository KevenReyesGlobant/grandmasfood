package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.mapper;

import ch.qos.logback.core.net.server.Client;
import org.grandmasfood.springcloud.products.domain.model.Product;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.request.ProductsCreateRequestDTO;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.response.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {
    
    Product toProduct(ProductsCreateRequestDTO productsCreateRequestDTO);

    ProductResponse toProductResponseDTO(Product product);


}
