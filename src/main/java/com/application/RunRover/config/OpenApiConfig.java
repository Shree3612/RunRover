package com.application.RunRover.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RunRover")
                        .description("RunRover, developed by Shripad Kulkarni, is a cricket app offering endpoints for managing players, teams, matches, and player profiles, providing detailed stats and information for cricket enthusiasts.")
                        .version("1.0")
                        .termsOfService("https://RunRover.com/terms")
                        .contact(new Contact()
                                .name("Shripad Kulkarni")
                                .url("https://github.com/Shree3612/ContentFlow/")
                                .email("shripadkulkarni3612@gmail.com"))
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
