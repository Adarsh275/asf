package com.examly.springapp.config;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerApiConfig {
 private static final String BEARER_AUTH = "bearerAuth";
 
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Book Heaven")
                        .description("Book Heaven is a comprehensive platform designed for book lovers. It offers a suite of features to enhance the book discovery and reading experience.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Team 2")
                                .email("book.heaven@yopmail.com")
                                .url("")) //after completing our url.
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(new Server().url("https://8080-dccfbfacadeffaabbaedacbaba.premiumproject.examly.io/")))
                .addSecurityItem(new SecurityRequirement()
                        .addList(BEARER_AUTH))
                .components(new Components()
                        .addSecuritySchemes(
                                BEARER_AUTH, new SecurityScheme()
                                        .name(BEARER_AUTH)
                                        .type(HTTP)
                                        .scheme("bearer")
                                        .description("Provide the JWT token.")
                                        .bearerFormat("JWT")));
    }
}
