package org.grandmasfood.springcloud.orders.application.ports.input;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "clients")
public interface IClientClientRest {
    @PostMapping("/api/v1/client")
    Client createClientRest(@RequestBody @Valid Client client);

    @GetMapping("/api/v1/{id}")
    Client readClientActiveById(@PathVariable @Valid Long id);

    @GetMapping("/api/v1/client/{document}")
    Client listClientActiveByDocuments(@PathVariable @Valid String document);
}

