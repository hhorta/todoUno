package com.todouno.kardex.core.config.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Open api config.
 */
@Configuration
public class OpenApiConfig {
    /**
     * Custom open api data open api.
     *
     * @return the open api
     */
    @Bean
    public OpenAPI customOpenAPIData() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Todo 1")
                        .description("Esta es la especificación de la REST API del sistema Todo 1")
                        .version("0.0.1"));
    }
}


