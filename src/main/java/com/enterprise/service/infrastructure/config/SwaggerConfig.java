package com.enterprise.service.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Swagger configuration class
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info().title("Enterprise Service API")
                        .description("This service is the primary API consumed by Customer User Interface (CUI) and other Internal Services " +
                                "(EIS) to obtain item information.")
                        .version("0.0.1-SNAPSHOT")
                        .contact(new Contact()
                                .name("Javier García")
                                .url("https://enterprise-service-api.com"))
                        .termsOfService("https://enterprise-service-api.com"))
                .servers(List.of(
                        new Server().url("//localhost:8081").description("Local server"),
                        new Server().url("//dev-enterprise-service-api.com").description("Development server"),
                        new Server().url("//qa-enterprise-service-api.com").description("QA server"),
                        new Server().url("//enterprise-service-api.com").description("Production server")));

    }
}