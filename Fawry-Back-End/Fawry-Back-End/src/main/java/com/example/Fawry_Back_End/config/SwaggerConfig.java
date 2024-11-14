package com.example.Fawry_Back_End.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("Fawry System API")
//                        .description("API documentation for Fawry System")
//                        .version("1.0"));
//    }
@Bean
public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .info(new Info().title("API Documentation").version("1.0"))
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new io.swagger.v3.oas.models.Components()
                    .addSecuritySchemes("bearerAuth",
                            new SecurityScheme()
                                    .name("Authorization")
                                    .type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")  // Ensure no spaces here
                                    .bearerFormat("JWT"))); // Optional, specifies the format of the token
}
}
