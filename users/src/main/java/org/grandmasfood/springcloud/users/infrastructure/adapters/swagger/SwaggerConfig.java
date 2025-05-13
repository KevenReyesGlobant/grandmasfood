package org.grandmasfood.springcloud.users.infrastructure.adapters.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API GRANDMASFOOD",
                description = "This application is built using a microservices architecture, enabling modular scalability, independent deployment, and efficient management of each component. It also implements a hexagonal architecture (Ports and Adapters), which decouples the business core from external technologies. The solution is orchestrated through an API Gateway based on Spring Cloud, providing dynamic routing, load balancing, and centralized request management to facilitate service integration and security.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Keven Reyes",
                        url = "https://portfoliothreekevenreyes.netlify.app/",
                        email = "keven.reyes@globant.com"
                ),
                license = @License(
                        name = "Standard Software Use License for Grandmasfood",
                        url = "https://github.com/KevenReyesGlobant/grandmasfood"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:3300/"
                ),
                @Server(
                        description = "PROD SERVER",
                        url = "http://localhost:3001/"
                )
        }
)

public class SwaggerConfig {

}
