package org.grandmasfood.springcloud.orders.config.interfaces;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "clients", url = "localhost:3000")
public interface IClientClientRest {
    @PostMapping("/clients")
    Client createClientRest(@RequestBody @Valid Client client);

    @GetMapping("/{id}")
    Client readClientActiveById(@PathVariable @Valid Long id);
}
