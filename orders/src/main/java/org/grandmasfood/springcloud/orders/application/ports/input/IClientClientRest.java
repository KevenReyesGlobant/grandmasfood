package org.grandmasfood.springcloud.orders.application.ports.input;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "clients", url = "localhost:3000/api/v1")
public interface IClientClientRest {
    @PostMapping("/client")
    Client createClientRest(@RequestBody @Valid Client client);

    @GetMapping("/{id}")
    Client readClientActiveById(@PathVariable @Valid Long id);

    @GetMapping("/client/{document}")
    Client listClientActiveByDocuments(@PathVariable @Valid String document);
}
