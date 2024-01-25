package com.parcelroute.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

/**
 * Configuration class for defining the OpenAPI specification for the Parcelflow API.
 */
@Configuration
public class OpenAPIConfig {

    @Value("${parcelflow.openapi.dev-url}")
    private String devUrl;

    @Value("${parcelflow.openapi.prod-url}")
    private String prodUrl;

    /**
     * Creates and configures an instance of the OpenAPI specification for the Parcelflow API.
     *
     * @return An instance of {@link io.swagger.v3.oas.models.OpenAPI} representing the API specification.
     */
    @Bean
    public OpenAPI myOpenAPI() {
        // Create server objects for development and production environments
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        // Create an Info object with title and version information
        Info info = new Info()
                .title("Parcelflow API")
                .version("1.0");

        // Create an instance of OpenAPI and configure it with the Info and servers
        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
