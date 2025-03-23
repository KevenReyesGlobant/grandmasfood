package org.grandmasfood.springcloud.products.infrastructure.adapters.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {

    @Bean
    public GroupedOpenApi productsApi() {
        return GroupedOpenApi.builder()
                .group("products")
                .packagesToScan("org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.controller.ProductsController")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GrandmasFood products API")
                        .version("1.0")
                        .description("Documentation for the microservice products into GrandmasFood"));
    }

}
