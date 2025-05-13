package org.grandmasfood.springcloud.users.infrastructure.adapters.swagger;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(
                title = "GRANDMASFOOD API",
                description = """
                        # GRANDMASFOOD Microservices Platform - Users Services
                        
                        ## Architecture Overview
                        This platform implements a modern cloud-native architecture with the following characteristics:
                        
                        * **Microservices Architecture**: Independent, loosely coupled services with dedicated databases
                        * **Hexagonal Design**: Business domain isolated from infrastructure through ports and adapters
                        * **API Gateway**: Centralized routing, load balancing, and security enforcement
                      
                        ## Service Boundaries
                        * **User Service**: Authentication, authorization, and user management
               
                     
                     
                        ## Technical Documentation
                        For detailed technical documentation including sequence diagrams, data models, and deployment guides:
                        [Technical Documentation](https://github.com/KevenReyesGlobant/grandmasfood)
                        
                        ## API Versioning
                        APIs follow semantic versioning (MAJOR.MINOR.PATCH) with backward compatibility guaranteed within the same MAJOR version.
                        """,
                version = "1.0.0",
                contact = @Contact(
                        name = "GRANDMASFOOD Engineering Team",
                        url = "https://github.com/KevenReyesGlobant/grandmasfood",
                        email = "keven.reyes@globant.com"
                ),
                license = @License(
                        name = "GRANDMASFOOD Proprietary License",
                        url = "https://github.com/KevenReyesGlobant/grandmasfood"
                ),
                termsOfService = "https://github.com/KevenReyesGlobant/grandmasfood"
        ),
        servers = {
                @Server(
                        description = "Development Environment",
                        url = "${api.dev.url:http://localhost:3300}",
                        variables = {
                                @ServerVariable(
                                        name = "protocol",
                                        defaultValue = "https",
                                        description = "API protocol",
                                        allowableValues = {"http", "https"}
                                )
                        }
                ),
                @Server(
                        description = "Staging Environment gateway",
                        url = "http://localhost:3001"
                )
        },

        externalDocs = @ExternalDocumentation(
                description = "GRANDMASFOOD API Developer Portal",
                url = "https://github.com/KevenReyesGlobant/grandmasfood"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "JWT Authorization header using Bearer scheme. Example: 'Bearer eyJhbGciOiJIUzI1NiIsIn...'"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addResponses("UnauthorizedError", new ApiResponse()
                                .description("Authentication information is missing or invalid"))
                        .addResponses("ForbiddenError", new ApiResponse()
                                .description("Insufficient permissions to perform this operation"))
                        .addResponses("NotFoundError", new ApiResponse()
                                .description("The requested resource was not found"))
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
                        .title("GRANDMASFOOD API")
                        .version("1.0.0"));
    }


    @Bean
    public OpenApiCustomizer securityCustomizer() {
        return openApi -> {
            SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

            openApi.getPaths().forEach((path, pathItem) -> {
                if (path.contains("/logged")) {
                    if (pathItem.getGet() != null) {
                        pathItem.getGet().addSecurityItem(securityRequirement);
                    }
                    if (pathItem.getPost() != null) {
                        pathItem.getPost().addSecurityItem(securityRequirement);
                    }
                    if (pathItem.getPut() != null) {
                        pathItem.getPut().addSecurityItem(securityRequirement);
                    }
                    if (pathItem.getDelete() != null) {
                        pathItem.getDelete().addSecurityItem(securityRequirement);
                    }
                    if (pathItem.getPatch() != null) {
                        pathItem.getPatch().addSecurityItem(securityRequirement);
                    }
                }
            });
        };
    }
}