package org.grandmasfood.springcloud.orders.infrastructure.adapters.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {

    @Bean
    public GroupedOpenApi ordersApi() {
        return GroupedOpenApi.builder()
                .group("orders")
                .packagesToScan("org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.controller.OrdersController")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GrandmasFood Orders API")
                        .version("1.0")
                        .description("Documentation for the microservice orders into GrandmasFood"));
    }

}
