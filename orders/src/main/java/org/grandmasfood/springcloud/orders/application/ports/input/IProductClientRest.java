package org.grandmasfood.springcloud.orders.application.ports.input;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "products", url = "localhost:3200")
public interface IProductClientRest {

    @PostMapping("/product")
    Product createProductRest(@RequestBody @Valid Product product);

    @GetMapping("/{id}")
    Product readProductActiveByID(@PathVariable @Valid Long id);
}
