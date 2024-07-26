package com.puce.java.gestionhospital.citas.configuration;

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
                        .title("Citas")
                        .version("1.0")
                        .description("Crea, actualiza, elimina, y obtiene a las citas de la base de datos"));
    }
}
