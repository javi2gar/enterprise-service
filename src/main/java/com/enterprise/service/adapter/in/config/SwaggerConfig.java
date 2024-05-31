package com.enterprise.service.adapter.in.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


import java.util.List;

/**
 * Swagger configuration class
 */
@Configuration
@PropertySource("classpath:application.properties")
public class SwaggerConfig {

    @Value("${spring.application.version}")
    private String version;

    @Value("${spring.application.organization}")
    private String organization;

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info().title("Enterprise Service API")
                        .description("This service is the primary API consumed by Customer User Interface (CUI) and other Internal Services " +
                                "(EIS) to obtain item information.")
                        .version(version)
                        .contact(new Contact()
                                .name(organization)
                                .email("javi2gar@gmail.com")
                                .url("https://enterprise-service-api.com"))
                        .termsOfService("https://enterprise-service-api.com"))
                .servers(List.of(
                        new Server().url("//localhost:8081").description("Local server"),
                        new Server().url("//dev-enterprise-service-api.com").description("Development server"),
                        new Server().url("//qa-enterprise-service-api.com").description("QA server"),
                        new Server().url("//enterprise-service-api.com").description("Production server")));

    }
}