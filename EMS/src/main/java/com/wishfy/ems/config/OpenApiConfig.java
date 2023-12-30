package com.wishfy.ems.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()

                .info(new Info().title("EMS")
                        .description("wishfy Task")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Shubham Dhokchaule")
                                .email("shubham.gmail@shruteekatech.com")
                                .url("https://github.com/Shubham61D/EMS_WISHFY")));
    }
}