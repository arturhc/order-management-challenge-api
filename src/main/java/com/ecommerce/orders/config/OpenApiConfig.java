package com.ecommerce.orders.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {

    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
        .components(new Components().addSecuritySchemes("BearerAuth",
            new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        ))
        .info(new Info()
            .title("Order Management API")
            .version("1.0")
            .description("API for managing e-commerce orders")
            .contact(new Contact()
                .name("Arturo Cordero")
                .email("arturh.sw@gmail.com")
                .url("https://easycommerce.com")));
  }

}