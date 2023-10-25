package com.example.springbootjwtsecurity.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes("Bearer Token", apiKey()))
                .info(new Info().title("DANIEL").description("Writen by: daniel_tamoe"))
                .security(List.of(new SecurityRequirement().addList("Bearer Token")));
    }

    private SecurityScheme apiKey() {
        return new SecurityScheme()
                .name("Authorization")
                .description("put your jwt token here!")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer");
    }
}