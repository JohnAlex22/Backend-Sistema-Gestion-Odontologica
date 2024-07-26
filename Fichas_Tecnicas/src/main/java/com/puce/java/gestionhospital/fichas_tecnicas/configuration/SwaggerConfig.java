package com.puce.java.gestionhospital.fichas_tecnicas.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fichas_Tecnicas")
                        .version("1.0")
                        .description("Crea, actualiza, elimina, y obtiene las fichas y tratamientos de la base de datos"));
    }
}
