package org.grandmasfood.springcloud.users.infrastructure.adapters.output.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API GRANDMASFOOD_USERS",
                description = "This app microservices provides a user management system",
                version = "1.0.0",
                contact = @Contact(
                        name = "Keven Reyes",
                        url = "https://portfoliothreekevenreyes.netlify.app/",
                        email = "keven.reyes@globant.com"
                ),
                license = @License(
                        name = "Standard Software Use License for Grandmasfood",
                        url = "https://portfoliothreekevenreyes.netlify.app/"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:3300"
                ),
                @Server(
                        description = "PROD SERVER",
                        url = "http://localhost:3300"
                )
        }
)

public class SwaggerConfig {

}
