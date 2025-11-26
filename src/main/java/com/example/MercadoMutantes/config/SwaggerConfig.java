package com.example.MercadoMutantes.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("API DE DETECCIÓN DE MUTANTES")
                        .description("Bienvenido a la API de Detección de Mutantes! La API que permite analizar cadenas de ADN para determinar si es humano o mutante. A su vez, indica la razón entre humanos y mutantes.")
                        .version("2.8.14")
                        .contact(new Contact()
                                .name("Lucía Milagros Rodriguez")
                                .email("luchirodriguez118@gmail.com")
                                .url("")));

    }
}