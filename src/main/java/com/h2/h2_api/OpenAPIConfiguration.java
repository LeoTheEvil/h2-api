package com.h2.h2_api;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8081");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Leonardo Sebastian Mancuso");
        myContact.setEmail("leomancuso8@gmail.com");

        Info information = new Info()
                .title("Book Management API")
                .version("1.0")
                .description("This API manages a book database.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}