package org.grandmasfood.springcloud.clients.utils;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {

    @Bean
    public GroupedOpenApi clientsApi() {
        return GroupedOpenApi.builder()
                .group("clients")
                .packagesToScan("org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.controller.ClientsController")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GrandmasFood Clients API")
                        .version("1.0")
                        .description("Documentation for the microservice clients into GrandmasFood"));
    }

}
