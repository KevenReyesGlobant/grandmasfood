package org.grandmasfood.springcloud.products.infrastructure.adapters.swagger;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(
                title = "GRANDMASFOOD API - Products Service",
                description = """
                        # GRANDMASFOOD Microservices Platform - Products Service

                        ## Architecture Overview
                        This service is part of a microservices-based architecture with the following characteristics:

                        * **Microservices Architecture**: Each service owns its logic and persistence
                        * **Hexagonal Architecture**: Separation of domain logic from infrastructure through Ports and Adapters
                        * **API Gateway Integration**: Centralized routing, security, and observability with Spring Cloud Gateway

                        ## Service Boundaries
                        * **Products Service**: Manages the creation, updating, retrieval, and deletion of product information

                        ## Technical Documentation
                        For full architecture, deployment, and data model information:
                        [GRANDMASFOOD GitHub](https://github.com/KevenReyesGlobant/grandmasfood)

                        ## Versioning
                        This API follows semantic versioning. Minor versions maintain backward compatibility.
                        """,
                version = "1.0.0",
                contact = @Contact(
                        name = "Keven Reyes",
                        url = "https://portfoliothreekevenreyes.netlify.app/",
                        email = "keven.reyes@globant.com"
                ),
                license = @License(
                        name = "Standard Software Use License for GRANDMASFOOD",
                        url = "https://github.com/KevenReyesGlobant/grandmasfood"
                )
        ),
        servers = {
                @Server(
                        description = "Development Server",
                        url = "${api.dev.url:http://localhost:3200}",
                        variables = {
                                @ServerVariable(
                                        name = "protocol",
                                        defaultValue = "http",
                                        description = "Protocol options",
                                        allowableValues = {"http", "https"}
                                )
                        }
                ),
                @Server(
                        description = "Gateway Server",
                        url = "http://localhost:3001"
                )
        },
        externalDocs = @ExternalDocumentation(
                description = "GRANDMASFOOD Full Documentation",
                url = "https://github.com/KevenReyesGlobant/grandmasfood"
        )
)
//@SecurityScheme(
//        name = "bearerAuth",
//        type = SecuritySchemeType.HTTP,
//        scheme = "bearer",
//        bearerFormat = "JWT",
//        description = "Use JWT token in Authorization header as: Bearer <token>"
//)
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addResponses("UnauthorizedError", new ApiResponse().description("JWT is missing or invalid"))
                        .addResponses("ForbiddenError", new ApiResponse().description("Access is denied due to invalid credentials"))
                        .addResponses("NotFoundError", new ApiResponse().description("Requested resource was not found"))
                        .addSchemas("ErrorResponse", new Schema<>()
                                .type("object")
                                .addProperties("status", new Schema<>().type("integer"))
                                .addProperties("code", new Schema<>().type("string"))
                                .addProperties("message", new Schema<>().type("string"))
                                .addProperties("timestamp", new Schema<>().type("string").format("date-time"))
                                .addProperties("path", new Schema<>().type("string"))
                                .addProperties("traceId", new Schema<>().type("string")))
                )
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("GRANDMASFOOD API - Products Service")
                        .version("1.0.0"));
    }

    @Bean
    public OpenApiCustomizer securityCustomizer() {
        return openApi -> {
            SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

            openApi.getPaths().forEach((path, pathItem) -> {
                if (path.contains("/admin") || path.contains("/secured") || path.contains("/logged")) {
                    if (pathItem.getGet() != null) pathItem.getGet().addSecurityItem(securityRequirement);
                    if (pathItem.getPost() != null) pathItem.getPost().addSecurityItem(securityRequirement);
                    if (pathItem.getPut() != null) pathItem.getPut().addSecurityItem(securityRequirement);
                    if (pathItem.getDelete() != null) pathItem.getDelete().addSecurityItem(securityRequirement);
                    if (pathItem.getPatch() != null) pathItem.getPatch().addSecurityItem(securityRequirement);
                }
            });
        };
    }

    @Bean
    public OpenApiCustomizer productExampleCustomizer() {
        return openApi -> {
            openApi.getPaths().forEach((path, pathItem) -> {
                if (path.contains("/products") && pathItem.getPost() != null) {
                    Example example1 = new Example()
                            .summary("Arabica Coffee Beans")
                            .description("Standard product example")
                            .value("""
                                {
                                  "name": "Premium Coffee Beans",
                                  "description": "High-quality Arabica beans from Colombia",
                                  "price": 15.99,
                                  "stock": 100,
                                  "category": "Coffee"
                                }
                                """);

                    Example example2 = new Example()
                            .summary("Fish cook fantasy product")
                            .description("Example with fantasy name and available flag")
                            .value("""
                                {
                                  "fantasyName": "Fish cook",
                                  "category": "CHICKEN",
                                  "description": "Delicious homemade cookies",
                                  "price": 6.99,
                                  "available": true
                                }
                                """);

                    if (pathItem.getPost().getRequestBody() != null &&
                            pathItem.getPost().getRequestBody().getContent() != null) {
                        pathItem.getPost().getRequestBody().getContent().forEach((mediaType, content) -> {
                            content.addExamples("arabica-example", example1);
                            content.addExamples("fish-cook-example", example2);
                        });
                    }
                }
            });
        };
    }

}
